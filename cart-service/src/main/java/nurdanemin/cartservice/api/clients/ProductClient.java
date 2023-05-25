package nurdanemin.cartservice.api.clients;

import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import org.apache.kafka.clients.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
@FeignClient(name="product-service", fallback = ProductClientFallback.class)
public interface ProductClient {
        //@Retry(name = "product-service")
        @GetMapping(value = "/api/products/product-price/{productId}")
        ProductClientResponse getProductPrice(@PathVariable UUID productId);
}
