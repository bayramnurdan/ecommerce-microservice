package nurdanemin.orderservice.business.abstracts;

import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<GetAllOrdersResponse> getAll();
    GetOrderResponse getById(UUID id);
    CreateOrderResponse add(CreateOrderRequest request);
    List<GetAllOrdersResponse> getAllOrdersOfUser(UUID userId);

}
