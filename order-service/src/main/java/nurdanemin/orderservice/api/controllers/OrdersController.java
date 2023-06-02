package nurdanemin.orderservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.orderservice.business.abstracts.OrderService;
import nurdanemin.orderservice.business.dto.request.create.CreateOrderRequest;
import nurdanemin.orderservice.business.dto.response.create.CreateOrderResponse;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrdersResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderResponse;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public CreateOrderResponse add(@RequestBody CreateOrderRequest request){
        return service.add(request);
    }

    @GetMapping("get-all-orders-of/{userId}")
    List<GetAllOrdersResponse> getAllOrdersOfUser(@PathVariable  UUID userId){
        return service.getAllOrdersOfUser(userId);
    }
}
