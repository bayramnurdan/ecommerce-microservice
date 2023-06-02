package nurdanemin.catalogservice.business.abstracts;

import nurdanemin.catalogservice.business.dto.request.create.CreateCategoryRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateCategoryRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllCategoriesResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateCategoryResponse;
import nurdanemin.catalogservice.entities.Category;
import nurdanemin.catalogservice.entities.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(UUID id);
    CreateCategoryResponse create(CreateCategoryRequest request);
    UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request);
    void delete(UUID id);
    Set<Category> getCategoriesAsSet(Set<UUID> idList);
    void addProductForCategories(Product product, Set<UUID>categoryIds);
}
