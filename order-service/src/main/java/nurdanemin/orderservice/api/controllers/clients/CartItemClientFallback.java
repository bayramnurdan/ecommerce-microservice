package nurdanemin.orderservice.api.controllers.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class CartItemClientFallback implements CartItemClient{
    @Override
    public GetCartItemResponse getCartItemById(UUID id) {
        return null;
    }
}
