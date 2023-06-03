package nurdanemin.shippingservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.order.OrderReadyForShippingEvent;
import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.shippingservice.business.abstracts.ShippingService;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.commonpackage.utils.dto.CreateShippingResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;
import nurdanemin.shippingservice.entities.Shipping;
import nurdanemin.shippingservice.entities.ShippingStatus;
import nurdanemin.shippingservice.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ShippingManager implements ShippingService {
    private final ShippingRepository repository;
    private final ModelMapperService mapper;
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
        shipping.setStatus(ShippingStatus.NOT_STARTED);
        var savedShipping = repository.save(shipping);
        return mapper.forResponse().map(savedShipping, CreateShippingResponse.class);
    }

    @Override
    public UpdateShippingResponse updateShipping(UUID id, UpdateShippingRequest request) {
        return null;
    }

    @Override
    public GetShippingResponse updateShippingStatus(UUID id, ShippingStatus status) {
       Shipping shipping = repository.findById(id).orElseThrow();
       shipping.setStatus(status);
       repository.save(shipping);
       return getById(id);
    }
}
