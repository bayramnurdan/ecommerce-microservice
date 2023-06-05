package nurdanemin.cartservice.business.abstracts;

import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
import nurdanemin.cartservice.business.dto.response.create.CreateShoppingCartResponse;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.commonpackage.utils.dto.GetShoppingCartResponse;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<GetAllShoppingCartsResponse> getAll();
    GetShoppingCartResponse getById(UUID id);
    CreateShoppingCartResponse add(UUID userId);
    GetShoppingCartResponse addtoCart(UUID cartId, CreateCartItemRequest request);
    GetShoppingCartResponse deleteItemFromCart(UUID shoppingCartId, UUID cartItemId);
    GetShoppingCartResponse updateItemQuantity(UUID shoppingCartId, UUID cartItemId, int quantity);
    GetShoppingCartResponse emptyCard(UUID cartId);



}