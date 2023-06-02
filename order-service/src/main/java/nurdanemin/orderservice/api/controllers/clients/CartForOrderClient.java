package nurdanemin.orderservice.api.controllers.clients;

import io.github.resilience4j.retry.annotation.Retry;
import nurdanemin.commonpackage.utils.dto.GetShoppingCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cart-service", fallback = CartForOrderClientFallback.class)
public interface CartForOrderClient {
    @Retry(name = "cart-retry")
    @GetMapping("api/carts/{cartId}")
    public GetShoppingCartResponse getById(@PathVariable UUID cartId);
}
