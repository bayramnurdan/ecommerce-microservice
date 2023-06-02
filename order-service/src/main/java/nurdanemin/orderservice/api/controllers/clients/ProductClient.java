package nurdanemin.orderservice.api.controllers.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name="catolog-service", fallback = ProductClientFallback.class)
public interface ProductClient {
    @PutMapping("/api/products/update-quantity/{productId}")
    public void updateQuantity(@PathVariable UUID productId, @RequestParam int updateAmount);
}
