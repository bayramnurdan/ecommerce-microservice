package nurdanemin.orderservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrderItemsResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderItemResponse;
import nurdanemin.orderservice.entities.Order;
import nurdanemin.orderservice.entities.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    List<GetAllOrderItemsResponse> getAll();
    GetOrderItemResponse getById(UUID id);
    OrderItem add(GetCartItemResponse request);
    void setOrderIdForItem(UUID OrderItemId, Order order);
}