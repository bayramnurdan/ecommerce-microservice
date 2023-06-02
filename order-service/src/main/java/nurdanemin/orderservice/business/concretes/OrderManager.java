package nurdanemin.orderservice.business.concretes;


import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.CommonMethods;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.orderservice.api.controllers.clients.CartForOrderClient;
import nurdanemin.orderservice.api.controllers.clients.CartItemClient;
import nurdanemin.orderservice.api.controllers.clients.PaymentClient;
import nurdanemin.orderservice.api.controllers.clients.ProductForOrderClient;
import nurdanemin.orderservice.business.abstracts.OrderItemService;
import nurdanemin.orderservice.business.abstracts.OrderService;
import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;
import nurdanemin.orderservice.entities.Order;
import nurdanemin.orderservice.entities.OrderItem;
import nurdanemin.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository repository;
    private final CartForOrderClient cartClient;
    private final CartItemClient cartItemClient;
    private final OrderItemService orderItemService;
    private final ModelMapperService mapper;
    private final PaymentClient paymentClient;
    private final ProductForOrderClient productClient;
    @Override
    public List<GetAllOrdersResponse> getAll() {
        return null;
    }

    @Override
    public GetOrderResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());

        var cartResponse = cartClient.getById(request.getCartId());
        Set<OrderItem> items = new HashSet<>();
        for (UUID id : cartResponse.getCartItemIds()) {
            var cartItemResponse = cartItemClient.getCartItemById(id);
            var orderItem = orderItemService.add(cartItemResponse);
            items.add(orderItem);
        }
        calculateTotalPrice(order);
        CreatePaymentResponse paymentResponse = paymentClient.add(request.getPaymentRequest());
        paymentClient.processPayment(new ProcessPaymentRequest(paymentResponse.getId(), order.getTotalOrderPrice()));

        var savedOrder = repository.save(order);
        updateQuantityForAllOrderItems(order);
        var response = mapper.forResponse().map(savedOrder, CreateOrderResponse.class);
        response.setOrderItems(CommonMethods.getItemsAsUUIDSet(savedOrder.getOrderItems()));
        return response;

    }




    @Override
    public List<GetAllOrdersResponse> getAllOrdersOfUser(UUID userId) {
        return null;
    }

    private void calculateTotalPrice(Order order){
        double sum = 0.0;
        for( OrderItem item :order.getOrderItems() ){
            sum += item.getPricePerUnit()* item.getProductQuantity();
        }
        order.setTotalOrderPrice(sum);
        repository.save(order);

    }
    private void updateQuantityForAllOrderItems(Order order){
        for (OrderItem item: order.getOrderItems()){
            productClient.updateQuantity(item.getProductId(), item.getProductQuantity());
        }
    }
}
