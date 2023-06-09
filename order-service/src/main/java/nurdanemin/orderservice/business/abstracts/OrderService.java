package nurdanemin.orderservice.business.abstracts;

import nurdanemin.commonpackage.events.shipping.ShippingCreatedEvent;
import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;
import nurdanemin.orderservice.entities.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<GetAllOrdersResponse> getAll();
    GetOrderResponse getById(UUID id);
    CreateOrderResponse add(CreateOrderRequest request);
    List<GetAllOrdersResponse> getAllOrdersOfCart(UUID cartId);
    void addShippingIdForOrder(ShippingCreatedEvent event);

}