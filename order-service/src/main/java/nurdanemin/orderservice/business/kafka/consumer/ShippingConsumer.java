package nurdanemin.orderservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.commonpackage.events.shipping.ShippingCreatedEvent;
import nurdanemin.orderservice.business.abstracts.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingConsumer {
    OrderService service;

    @KafkaListener(topics = "shipping-created", groupId = "shipping-create")
    public void consume(ShippingCreatedEvent event) {
        service.addShippingIdForOrder(event);
        log.info(" shipping created event consumed {}", event);
    }
}