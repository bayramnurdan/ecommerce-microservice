package nurdanemin.cartservice.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
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

    @PutMapping("/add-to-cart")
    public GetShoppingCartResponse addtoCart(@Valid @RequestBody CreateCartItemRequest request){
        return service.addtoCart(request);

    }

    @PutMapping("/delete-item-from-cart/{shoppingCartId}")
    public GetShoppingCartResponse deleteItemFromCart(@PathVariable UUID shoppingCartId, @RequestParam UUID cartItemId){
        return service.deleteItemFromCart(shoppingCartId, cartItemId);
    }
    @PutMapping("/update-item-quantity/{shoppingCartId}")
    GetShoppingCartResponse updateItemQuantity(@PathVariable UUID shoppingCartId, @RequestParam UUID cartItemId, int quantity){
        return service.updateItemQuantity(shoppingCartId, cartItemId, quantity);
    }


}
