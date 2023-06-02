package nurdanemin.cartservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
@FeignClient(name="catalog-service")
public interface ProductClient {
        @Retry(name = "product-retry")
        @GetMapping(value = "/api/products/info/{productId}")
        ProductClientResponse getProductInfo(@PathVariable UUID productId);
}
