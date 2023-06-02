package nurdanemin.cartservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.cartservice.business.abstracts.CartItemService;
import nurdanemin.cartservice.business.dto.response.get.GetAllCartItemsResponse;
import nurdanemin.commonpackage.utils.dto.GetCartItemResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@AllArgsConstructor
@RequestMapping("/api/cartitems")
public class CarItemsController {
    private final CartItemService service;
    @GetMapping
    List<GetAllCartItemsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping
    GetCartItemResponse getCartItemById(UUID id){
        return service.getById(id);
    }
}
