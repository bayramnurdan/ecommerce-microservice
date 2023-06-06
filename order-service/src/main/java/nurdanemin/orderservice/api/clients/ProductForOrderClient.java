package nurdanemin.orderservice.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name="catolog-service", fallback = ProductForOrderClientFallback.class)
public interface ProductForOrderClient {
    @PutMapping("/api/products/update-quantity/{productId}")
    public void updateQuantity(@PathVariable UUID productId, @RequestParam int updateAmount);
}