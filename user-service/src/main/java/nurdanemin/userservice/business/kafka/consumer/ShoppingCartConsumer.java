package nurdanemin.userservice.business.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.catalog.ProductCreatedEvent;
import nurdanemin.commonpackage.events.shoppingcart.ShoppingCartCreatedEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.userservice.business.abstracts.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ShoppingCartConsumer {
    private final UserService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "shopping-cart-created",
            groupId = "shopping-cart-create"
    )
    public void consume(ShoppingCartCreatedEvent event) {
        service.addCartForUser(event.getUserId(), event.getCartId());
        log.info("Shopping cart  created event consumed {}", event);
    }
}