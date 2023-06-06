package nurdanemin.orderservice.business.concretes;


import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.order.OrderCreatedForInvoiceEvent;
import nurdanemin.commonpackage.events.order.OrderReadyForShippingEvent;
import nurdanemin.commonpackage.events.order.OrderReceivedForShippingEvent;
import nurdanemin.commonpackage.utils.CommonMethods;
import nurdanemin.commonpackage.utils.dto.*;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.orderservice.api.clients.CartForOrderClient;
import nurdanemin.orderservice.api.clients.PaymentClient;
import nurdanemin.orderservice.api.clients.ProductForOrderClient;
import nurdanemin.orderservice.business.abstracts.OrderItemService;
import nurdanemin.orderservice.business.abstracts.OrderService;
import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;
import nurdanemin.orderservice.entities.Order;
import nurdanemin.orderservice.entities.OrderItem;
import nurdanemin.orderservice.entities.OrderStatus;
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
    private final OrderItemService orderItemService;
    private final ModelMapperService mapper;
    private final PaymentClient paymentClient;
    private final ProductForOrderClient productClient;
    private final KafkaProducer producer;
    @Override
    public List<GetAllOrdersResponse> getAll() {
        return repository.findAll().stream()
                .map(order-> mapper.forResponse().map(order, GetAllOrdersResponse.class)).toList();
    }

    @Override
    public GetOrderResponse getById(UUID id) {
        var order = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(order, GetOrderResponse.class);
        response.setOrderItems(CommonMethods.getItemsAsUUIDSet(order.getOrderItems()));
        return response;
    }

    @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        order.setCartId(request.getCartId());

        var cartResponse = cartClient.getById(request.getCartId());
        Set<OrderItem> items = new HashSet<>();
        for (UUID id : cartResponse.getCartItemIds()) {
            var cartItemResponse = cartClient.getCartItemById(id);
            var orderItem = orderItemService.add(cartItemResponse);
            items.add(orderItem);
        }
        order.setTotalOrderPrice(cartResponse.getTotalPrice());

        CreatePaymentResponse paymentResponse = paymentClient.add(request.getPaymentRequest());
        paymentClient.processPayment(new ProcessPaymentRequest(paymentResponse.getId(), order.getTotalOrderPrice()));

        order.setStatus(OrderStatus.ORDER_SUCCESFULL);
        var savedOrder = repository.save(order);

        cartClient.emptyCard(request.getCartId());
        updateQuantityForAllOrderItems(order);

        sendOrderCreatedForInvoiceEvent(cartResponse, paymentResponse, savedOrder );

        sendOrderReceivedForShipping(request.getShippingRequest(), savedOrder.getId());

        var response = mapper.forResponse().map(savedOrder, CreateOrderResponse.class);
        response.setCartId(request.getCartId());
        response.setOrderItems(CommonMethods.getItemsAsUUIDSet(savedOrder.getOrderItems()));
        return response;
    }




    @Override
    public List<GetAllOrdersResponse> getAllOrdersOfCart(UUID cartId) {
        return repository.findAllByCartId(cartId)
                .stream()
                .map(order -> mapper.forResponse().map(order, GetAllOrdersResponse.class))
                .toList();
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
            productClient.updateQuantity(item.getProductId(), -1* (item.getProductQuantity()));
        }
    }

    private void sendOrderCreatedForInvoiceEvent(GetShoppingCartResponse cartResponse, CreatePaymentResponse paymentRequest, Order order){
        OrderCreatedForInvoiceEvent event = new OrderCreatedForInvoiceEvent();
        event.setCustomerFirstName(cartResponse.getUserFirstName());
        event.setCustomerLastName(cartResponse.getUserLastName());
        event.setCardHolder(paymentRequest.getCardHolder());
        event.setTotalAmount(order.getTotalOrderPrice());
        event.setOrderedAt(order.getOrderedAt());
        producer.sendMessage(event, "order-created-for-invoice");
    }

    private void sendOrderReadyForShippingEvent(Order order){
        OrderReadyForShippingEvent  event = new OrderReadyForShippingEvent();
        event.setShippingId(order.getShippingId());
        producer.sendMessage(event, "order-ready-for-shipping-created");
    }

    private void sendOrderReceivedForShipping(CreateShippingRequest request, UUID orderId){
        OrderReceivedForShippingEvent event = mapper.forResponse().map(request, OrderReceivedForShippingEvent.class);
        event.setOrderId(orderId);
        producer.sendMessage(event, "order-received-for-shipping");
    }

}