package nurdanemin.cartservice.business.abstracts;

import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
import nurdanemin.cartservice.business.dto.response.get.GetAllCartItemsResponse;
import nurdanemin.cartservice.entities.CartItem;
import nurdanemin.cartservice.entities.ShoppingCart;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    List<GetAllCartItemsResponse> getAll();
    CartItem getCartItemById(UUID id);

    CartItem createCartItem(CreateCartItemRequest request, ShoppingCart cart);
    void updateQuantity(UUID cartItemId, int quantity);
    void deleteCartItem(UUID id);
}
