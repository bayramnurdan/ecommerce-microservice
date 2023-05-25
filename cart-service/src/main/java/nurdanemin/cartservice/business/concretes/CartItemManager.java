package nurdanemin.cartservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.cartservice.business.abstracts.CartItemService;
import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
import nurdanemin.cartservice.business.dto.response.get.GetAllCartItemsResponse;
import nurdanemin.cartservice.entities.CartItem;
import nurdanemin.cartservice.entities.ShoppingCart;
import nurdanemin.cartservice.repository.CartItemRepository;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CartItemManager implements CartItemService {
    private final CartItemRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllCartItemsResponse> getAll() {
        List<CartItem> cartItems = repository.findAll();
       return    cartItems
                .stream()
                .map(cartItem -> mapper.forResponse().map(cartItem, GetAllCartItemsResponse.class))
                .toList();

    }

    @Override
    public CartItem getCartItemById(UUID id) {
        return  repository.findById(id).orElseThrow();
    }



    @Override
    public CartItem createCartItem(CreateCartItemRequest request) {
        var item = mapper.forRequest().map(request, CartItem.class);
        item.setId(UUID.randomUUID());
        return repository.save(item);
    }

    @Override
    public void setCartForCartItem(CartItem cartItem, ShoppingCart cart) {

    }

    @Override
    public void updateQuantity(UUID cartItemId, int quantity) {
        var item = repository.findById(cartItemId).orElseThrow();
        item.setProductQuantity(quantity);
        repository.save(item);
    }
}
