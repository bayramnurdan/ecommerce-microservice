package nurdanemin.invoiceservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.order.OrderCreatedEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "order-created",
            groupId = "order-create"
    )
    public void consume(OrderCreatedEvent event) {
        service.add(event);
        log.info("Order-created event consumed {}", event);
    }
}