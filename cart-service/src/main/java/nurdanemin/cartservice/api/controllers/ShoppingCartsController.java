package nurdanemin.cartservice.api.controllers;

import lombok.RequiredArgsConstructor;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.cartservice.business.dto.response.get.GetShoppingCartResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartsController {
    private final ShoppingCartService service;

    @GetMapping
    public List<GetAllShoppingCartsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetShoppingCartResponse getById(UUID id){
        return  service.getById(id);
    }


}
