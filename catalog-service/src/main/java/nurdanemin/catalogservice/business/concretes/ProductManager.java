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
import nurdanemin.commonpackage.events.catalog.ProductQuantityUpdatedEvent;
import nurdanemin.commonpackage.utils.enums.ProductState;
import nurdanemin.catalogservice.repository.ProductRepository;
import nurdanemin.commonpackage.events.catalog.ProductCreatedEvent;
import nurdanemin.commonpackage.utils.CommonMethods;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return  products
                .stream()
                .map(product -> mapper.forResponse().map(product, GetAllProductsResponse.class))
                .toList();
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfProductExists(id);
        var product = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(product, GetProductResponse.class);
        response.setCategoryIds(CommonMethods.getItemsAsUUIDSet(product.getCategories()));
        return response;
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        rules.checkIfProductExistByNameAndBrand(request.getName(), request.getBrandId());
        var product = mapper.forRequest().map(request, Product.class);
        product.setId(UUID.randomUUID());
        product.setState(ProductState.INSTOCK);
        product.setCategories(categoryService.getCategoriesAsSet(request.getCategoryIds()));
        var createdProduct = repository.save(product);
        sendKafkaProductCreatedEvent(createdProduct);
        categoryService.addProductForCategories(createdProduct, CommonMethods.getItemsAsUUIDSet(product.getCategories()));
        var response = mapper.forResponse().map(createdProduct, CreateProductResponse.class);
        response.setCategoryIds(CommonMethods.getItemsAsUUIDSet(createdProduct.getCategories()));
        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        var product = mapper.forRequest().map(request, Product.class);
        product.setId(id);

        var updatedProduct = repository.save(product);
        return mapper.forResponse().map(updatedProduct, UpdateProductResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfProductExists(id);
        repository.deleteById(id);
    }

    @Override
    public ProductClientResponse getProductInfo(UUID productId) {
        var response = new ProductClientResponse();
        try{
            rules.checkIfProductExists(productId);
            response.setSuccess(true);
            Product product = repository.findById(productId).orElseThrow();
            response.setProductPrice(product.getPrice());
            response.setProductName(product.getName());
            response.setProductState(product.getState());

        }catch(BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
            response.setProductPrice(0.0);
        }
        return response;
    }

    @Override
    public void updateQuantity(UUID productId, int updateAmount) {
        Product product = repository.findById(productId).orElseThrow();
        product.setAmount(product.getAmount()+updateAmount);
        setProductState(product);
        Product savedproduct = repository.save(product);
        sendKafkaProductAmountUpdatedEvent(savedproduct);

    }

    @Override
    public void setProductState(Product product) {
        if (product.getAmount()==0){
            product.setState(ProductState.NOTINSTOCK);
        }else{
            product.setState(ProductState.INSTOCK);
        }
        repository.save(product);
    }


    private Set<String> mapCategoriesToNameSet(Product product){
        Set<String> names = new HashSet<>();
        for(Category category: product.getCategories()){
            names.add(category.getName());
        }
        return names;
    }



    private void sendKafkaProductCreatedEvent(Product createdProduct) {
        var event = mapper.forResponse().map(createdProduct, ProductCreatedEvent.class);
        event.setCategoryIds(CommonMethods.getItemsAsUUIDSet(createdProduct.getCategories()));
        event.setCategoryNames(mapCategoriesToNameSet(createdProduct));
        producer.sendMessage(event, "product-created");
    }
    private void sendKafkaProductAmountUpdatedEvent(Product product){
        ProductQuantityUpdatedEvent event = new ProductQuantityUpdatedEvent();
        event.setProductId(product.getId());
        event.setQuantity(product.getAmount());
        producer.sendMessage(event, "product-amount-updated");
    }

}