package nurdanemin.orderservice.api.controllers.clients;

import io.github.resilience4j.retry.annotation.Retry;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@FeignClient(name = "cart-service", fallback = CartItemClientFallback.class)
public interface CartItemClient {
    @Retry(name = "cartitem-retry")
    @GetMapping("api/cartitems/")
    GetCartItemResponse getCartItemById(UUID id);
}
