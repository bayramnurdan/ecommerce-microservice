package nurdanemin.catalogservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.CategoryService;
import nurdanemin.catalogservice.business.abstracts.ProductService;
import nurdanemin.catalogservice.business.dto.request.create.CreateProductRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateProductRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateProductResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllProductsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetProductResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateProductResponse;
import nurdanemin.catalogservice.business.rules.ProductBusinessRules;
import nurdanemin.catalogservice.entities.Category;
import nurdanemin.catalogservice.entities.Product;
import nurdanemin.catalogservice.repository.ProductRepository;
import nurdanemin.commonpackage.events.catalog.ProductCreatedEvent;
import nurdanemin.commonpackage.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapperService mapper;
    private final ProductBusinessRules rules;
    private final CategoryService categoryService;
    private final KafkaProducer producer;
    @Override
    public List<GetAllProductsResponse> getAll() {
        var products = repository.findAll();
        var response = products
                .stream()
                .map(product -> mapper.forResponse().map(product, GetAllProductsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfProductExists(id);
       var product = repository.findById(id).orElseThrow();
       var response = mapper.forResponse().map(product, GetProductResponse.class);
       response.setCategoryIds(mapCategoriesToIdList(product));
       return response;
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        rules.checkIfProductExistByNameAndBrand(request.getName(), request.getBrandId());
       var product = mapper.forRequest().map(request, Product.class);
       product.setId(UUID.randomUUID());
       product.setCategories(categoryService.getCategoriesAsList(request.getCategoryIds()));
       var createdProduct = repository.save(product);
       sendKafkaProductCreatedEvent(createdProduct);
       categoryService.addProductForCategories(createdProduct, mapCategoriesToIdList(product));
       var response = mapper.forResponse().map(createdProduct, CreateProductResponse.class);
       response.setCategoryIds(mapCategoriesToIdList(createdProduct));
       return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        var product = mapper.forRequest().map(request, Product.class);
        product.setId(id);

        var updatedProduct = repository.save(product);
        var response = mapper.forResponse().map(updatedProduct, UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfProductExists(id);
        repository.deleteById(id);
    }

    @Override
    public ProductClientResponse getProductPrice(UUID productId) {
        var response = new ProductClientResponse();
        try{
            rules.checkIfProductExists(productId);
            response.setSuccess(true);
            response.setProductPrice(getPrice(productId));
        }catch(BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
            response.setProductPrice(0.0);
        }
        return response;
    }

    private List<UUID> mapCategoriesToIdList(Product product){
        List<UUID> ids = new ArrayList<>();
        for(Category category: product.getCategories()){
            ids.add(category.getId());
        }
        return ids;
    }
    private List<String> mapCategoriesToNameList(Product product){
        List<String> names = new ArrayList<>();
        for(Category category: product.getCategories()){
            names.add(category.getName());
        }
        return names;
    }



    private void sendKafkaProductCreatedEvent(Product createdProduct) {
        var event = mapper.forResponse().map(createdProduct, ProductCreatedEvent.class);
        event.setCategoryIds(mapCategoriesToIdList(createdProduct));
        event.setCategoryNames(mapCategoriesToNameList(createdProduct));
        producer.sendMessage(event, "product-created");
    }

    private double getPrice(UUID productId){
        var product = repository.findById(productId);
        return product.get().getPrice();
    }
}
