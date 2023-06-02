package nurdanemin.shippingservice.business.concretes;

import nurdanemin.commonpackage.utils.dto.CreateShippingRequest;
import nurdanemin.shippingservice.business.abstracts.ShippingService;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.shippingservice.business.dto.response.create.CreateShippingResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;

import java.util.List;

public class ShippingManager implements ShippingService {
    @Override
    public List<GetAllShippingsResponse> getAll() {
        return null;
    }

    @Override
    public GetShippingResponse getById(Long id) {
        return null;
    }

    @Override
    public CreateShippingResponse createShipping(CreateShippingRequest request) {
        return null;
    }

    @Override
    public UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request) {
        return null;
    }
}
