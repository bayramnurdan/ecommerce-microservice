package nurdanemin.orderservice.api.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public void updateQuantity(UUID productId, int updateAmount) {

    }
}