package nurdanemin.shippingservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.CreateShippingRequest;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.shippingservice.business.dto.response.create.CreateShippingResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;
import nurdanemin.shippingservice.entities.Shipping;

import java.util.List;

public interface ShippingService {
    List<GetAllShippingsResponse> getAll();
    GetShippingResponse getById(Long id);

    CreateShippingResponse createShipping(CreateShippingRequest request);
    UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request);
}
