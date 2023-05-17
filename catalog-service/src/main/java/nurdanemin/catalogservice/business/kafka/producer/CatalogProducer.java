package nurdanemin.catalogservice.business.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.catalog.ProductCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class CatalogProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ProductCreatedEvent event) {
        log.info(String.format("product-created event => %s", event.toString()));
        Message<ProductCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-created")
                .build();

        kafkaTemplate.send(message);
        System.out.println("gönderdim gitti");
    }
}
