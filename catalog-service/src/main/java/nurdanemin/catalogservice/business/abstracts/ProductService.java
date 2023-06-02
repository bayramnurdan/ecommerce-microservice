package nurdanemin.catalogservice.business.abstracts;

import nurdanemin.catalogservice.business.dto.request.create.CreateProductRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateProductRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateProductResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllProductsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetProductResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateProductResponse;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import org.apache.kafka.clients.ClientResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse create(CreateProductRequest request);
    UpdateProductResponse update(UUID id, UpdateProductRequest request);
    void delete(UUID id);
    ProductClientResponse getProductInfo(UUID productId);

}
