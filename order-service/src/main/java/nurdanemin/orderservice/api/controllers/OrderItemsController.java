package nurdanemin.orderservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.orderservice.business.abstracts.OrderItemService;
import nurdanemin.orderservice.business.dto.response.get.GetAllOrderItemsResponse;
import nurdanemin.orderservice.business.dto.response.get.GetOrderItemResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/orderitems")
public class OrderItemsController {
    private final OrderItemService service;
    @GetMapping
    public  List<GetAllOrderItemsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    GetOrderItemResponse getById(@PathVariable  UUID id){
        return service.getById(id);
    }

}