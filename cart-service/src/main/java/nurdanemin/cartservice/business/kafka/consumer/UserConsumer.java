package nurdanemin.cartservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.commonpackage.events.user.UserCreatedEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConsumer {
    private final ShoppingCartService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "user-created",
            groupId = "user-create"
    )
    public void consume(UserCreatedEvent event) {
        service.add(event.getUserId());
        log.info("User created event consumed {}", event);
    }
}


