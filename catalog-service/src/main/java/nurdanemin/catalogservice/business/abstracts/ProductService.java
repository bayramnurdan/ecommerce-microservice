package nurdanemin.catalogservice.business.abstracts;

import nurdanemin.catalogservice.business.dto.request.create.CreateProductRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateProductRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateProductResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllProductsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetProductResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateProductResponse;
import nurdanemin.catalogservice.entities.Product;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse create(CreateProductRequest request);
    UpdateProductResponse update(UUID id, UpdateProductRequest request);
    void delete(UUID id);
    ProductClientResponse getProductInfo(UUID productId);
    void updateQuantity(UUID productId, int updateAmount);
    void setProductState(Product product);

}