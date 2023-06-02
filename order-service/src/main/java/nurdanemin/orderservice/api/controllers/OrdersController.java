package nurdanemin.orderservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.orderservice.business.abstracts.OrderService;
import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrderService service;
    @GetMapping
    public List<GetAllOrdersResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    GetOrderResponse getById(UUID id){
        return service.getById(id);
    }

    public CreateOrderResponse add(CreateOrderRequest request){
        return service.add(request);
    }
    List<GetAllOrdersResponse> getAllOrdersOfUser(UUID userId){
        return service.getAllOrdersOfUser(userId);
    }
}
