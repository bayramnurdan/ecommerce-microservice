package nurdanemin.orderservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.orderservice.business.abstracts.OrderItemService;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrderItemsResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderItemResponse;
import nurdanemin.orderservice.entities.Order;
import nurdanemin.orderservice.entities.OrderItem;
import nurdanemin.orderservice.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllOrderItemsResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(orderItem -> mapper.forResponse().map(orderItem, GetAllOrderItemsResponse.class))
                .toList();
    }

    @Override
    public GetOrderItemResponse getById(UUID id) {
        var orderItem = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(orderItem, GetOrderItemResponse.class);
    }

    @Override
    public OrderItem add(GetCartItemResponse request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(UUID.randomUUID());
        orderItem.setPricePerUnit(request.getPricePerUnit());
        orderItem.setProductId(request.getProductId());
        orderItem.setProductName(request.getProductName());
        orderItem.setProductQuantity(request.getProductQuantity());
        return repository.save(orderItem);

    }
    public void setOrderIdForItem(UUID OrderItemId, Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        repository.save(orderItem);

    }
}