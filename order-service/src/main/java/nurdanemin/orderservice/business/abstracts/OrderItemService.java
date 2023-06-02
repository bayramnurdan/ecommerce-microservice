package nurdanemin.orderservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrderItemsResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderItemResponse;
import nurdanemin.orderservice.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<GetAllOrderItemsResponse> getAll();
    GetOrderItemResponse getById(Long id);
    OrderItem add(GetCartItemResponse request);
}
