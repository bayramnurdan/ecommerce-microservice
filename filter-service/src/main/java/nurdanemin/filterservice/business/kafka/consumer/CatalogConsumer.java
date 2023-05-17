package nurdanemin.filterservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.catalog.ProductCreatedEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.filterservice.business.abstracts.FilterService;
import nurdanemin.filterservice.entities.Filter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "product-created",
            groupId = "product-create"
    )
    public void consume(ProductCreatedEvent event) {
        System.out.println("Geldi");
        var filter = mapper.forRequest().map(event, Filter.class);
        filter.setCategoryIds(event.getCategoryIds());
        filter.setCategoryNames(event.getCategoryNames());
        service.add(filter);
        log.info("Product created event consumed {}", event);
    }
}
