package nurdanemin.orderservice.api.controllers.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import nurdanemin.commonpackage.utils.dto.GetShoppingCartResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class CartForOrderClientFallback implements CartForOrderClient {
    @Override
    public GetShoppingCartResponse getById(UUID cartId) {
        return null;
    }

    @Override
    public GetCartItemResponse getCartItemById(UUID id) {
        return null;
    }

    @Override
    public void emptyCard(UUID cartId) {

    }
}
