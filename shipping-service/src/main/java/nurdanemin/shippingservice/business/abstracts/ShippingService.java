package nurdanemin.shippingservice.business.abstracts;

import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.commonpackage.utils.dto.CreateShippingResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;
import nurdanemin.shippingservice.entities.ShippingStatus;

import java.util.List;
import java.util.UUID;

public interface ShippingService {
    List<GetAllShippingsResponse> getAll();
    GetShippingResponse getById(UUID id);

    CreateShippingResponse add(OrderReceivedForShippingEvent event);
    UpdateShippingResponse updateShipping(UUID id, UpdateShippingRequest request);
    GetShippingResponse updateShippingStatus(UUID id, ShippingStatus status);
}
