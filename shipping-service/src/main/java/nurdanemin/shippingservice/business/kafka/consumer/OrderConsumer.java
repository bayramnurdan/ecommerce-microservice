package nurdanemin.shippingservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.order.OrderReadyForShippingEvent;
import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.shippingservice.business.abstracts.ShippingService;
import nurdanemin.shippingservice.entities.ShippingStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final ShippingService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "order-ready-for-shipping-created",
            groupId = "order-ready-for-shipping-create"
        )
    public void consume(OrderReadyForShippingEvent event) {
        service.updateShippingStatus(event.getShippingId(), ShippingStatus.RECEIVED);
        log.info("order-ready-for-shipping-created event consumed {}", event);
        }
    }

    @KafkaListener(
            topics = "order-received-for-shipping",
            groupId = "order-receive-for-shipping"
    )
    public void consume(OrderReceivedForShippingEvent event) {
        service.add(event);
        log.info("order-received-for-shipping event consumed {}", event);
    }
}


