package nurdanemin.orderservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import nurdanemin.commonpackage.utils.dto.GetShoppingCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "cart-service", fallback = CartForOrderClientFallback.class)
public interface CartForOrderClient {
    @Retry(name = "cart-retry")
    @GetMapping("api/shoppingcarts/{cartId}")
    public GetShoppingCartResponse getById(@PathVariable UUID cartId);

    @Retry(name = "cartitem-retry")
    @GetMapping("api/cartitems/{id}")
    GetCartItemResponse getCartItemById(@PathVariable UUID id);

    @Retry(name="cart-empty-retry")
    @PutMapping("api/shoppingcarts/empty-cart/{cartId}")
    public void emptyCard(@PathVariable  UUID cartId);
}