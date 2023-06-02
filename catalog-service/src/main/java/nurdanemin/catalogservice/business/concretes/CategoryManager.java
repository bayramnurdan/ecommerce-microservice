package nurdanemin.catalogservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.CategoryService;
import nurdanemin.catalogservice.business.dto.request.create.CreateCategoryRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateCategoryRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllCategoriesResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateCategoryResponse;
import nurdanemin.catalogservice.business.rules.CategoryBusinessRules;
import nurdanemin.catalogservice.entities.Category;
import nurdanemin.catalogservice.entities.Product;
import nurdanemin.catalogservice.repository.CategoryRepository;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CategoryManager  implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryBusinessRules rules;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllCategoriesResponse> getAll() {
        var categories = repository.findAll();
        var response = categories
                .stream()
                .map(category -> mapper.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();
        return response;

    }

    @Override
    public GetCategoryResponse getById(UUID id) {
        rules.checkIfCategoryExists(id);
       var category = repository.findById(id);
       var response =mapper.forResponse().map(category, GetCategoryResponse.class);
       return response;
    }

    @Override
    public CreateCategoryResponse create(CreateCategoryRequest request) {
      rules.checkIfCategoryExistByName(request.getName());
      var category = mapper.forRequest().map(request, Category.class);
      category.setId(UUID.randomUUID());
      var createdCategory = repository.save(category);
      var response = mapper.forResponse().map(createdCategory, CreateCategoryResponse.class);
      return response;
    }

    @Override
    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExists(id);
        var category = mapper.forRequest().map(request, Category.class);
        category.setId(id);
        var updatedCategory = repository.save(category);
        var response = mapper.forResponse().map(updatedCategory, UpdateCategoryResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCategoryExists(id);
        repository.deleteById(id);

    }



    public Set<Category> getCategoriesAsSet(Set<UUID> idList){
        HashSet<Category> response = new HashSet();
        for(UUID id:idList){
            response.add(repository.findById(id).orElseThrow());

        }
        return response;
    }

    @Override
    public void addProductForCategories(Product product, Set<UUID>categoryIds) {
        for(UUID id : categoryIds){
            Category category = repository.findById(id).orElseThrow();
            var categoryProducts = category.getProducts();
            categoryProducts.add(product);
            repository.save(category);

        }


    }

}
