package nurdanemin.shippingservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.order.OrderReadyForShippingEvent;
import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.commonpackage.events.shipping.ShippingCreatedEvent;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.shippingservice.business.abstracts.ShippingService;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.commonpackage.utils.dto.CreateShippingResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;
import nurdanemin.shippingservice.business.rules.ShippingBusinessRules;
import nurdanemin.shippingservice.entities.Shipping;
import nurdanemin.shippingservice.entities.ShippingStatus;
import nurdanemin.shippingservice.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ShippingManager implements ShippingService {
    private final ShippingRepository repository;
    private final ModelMapperService mapper;
    private final ShippingBusinessRules rules;
    private final KafkaProducer producer;
    @Override
    public List<GetAllShippingsResponse> getAll() {
        return repository.findAll().stream()
                .map(shipping -> mapper.forResponse().map(shipping, GetAllShippingsResponse.class))
                .toList();
    }

    @Override
    public GetShippingResponse getById(UUID id) {
        return mapper.forResponse().map(repository.findById(id).orElseThrow(), GetShippingResponse.class);
    }

    @Override
    public CreateShippingResponse add(OrderReceivedForShippingEvent event) {
        Shipping shipping = mapper.forRequest().map(event, Shipping.class);
        shipping.setId(UUID.randomUUID());
        shipping.setOrderId(event.getOrderId());
        shipping.setStatus(ShippingStatus.NOT_STARTED);
        var savedShipping = repository.save(shipping);

        return mapper.forResponse().map(savedShipping, CreateShippingResponse.class);
    }

    @Override
    public UpdateShippingResponse updateShipping(UUID id, UpdateShippingRequest request) {
        rules.checkIfShippingSuitableForUpdate(id);
        Shipping shipping = mapper.forRequest().map(request, Shipping.class);
        shipping.setId(id);
        var savedShipping = repository.save(shipping);
        return mapper.forResponse().map(savedShipping, UpdateShippingResponse.class);
    }

    @Override
    public GetShippingResponse updateShippingStatus(UUID id, ShippingStatus status) {
        Shipping shipping = repository.findById(id).orElseThrow();
        if (status.equals(ShippingStatus.RECEIVED)){
            shipping.setCreatedAt(LocalDateTime.now());
        }
        shipping.setStatus(status);
        repository.save(shipping);
        return getById(id);
    }

    private void sendShippingCreatedEvent(Shipping shipping){
        ShippingCreatedEvent event = new ShippingCreatedEvent();
        event.setShippingId(shipping.getId());
        event.setOrderId(shipping.getOrderId());
        producer.sendMessage(event, "shipping-created");
    }
}