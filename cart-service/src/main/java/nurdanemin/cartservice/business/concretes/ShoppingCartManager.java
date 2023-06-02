package nurdanemin.cartservice.business.concretes;


import lombok.AllArgsConstructor;

import nurdanemin.cartservice.business.abstracts.CartItemService;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
import nurdanemin.cartservice.business.dto.response.ShoppingCartResponseDto;
import nurdanemin.cartservice.business.dto.response.create.CreateShoppingCartResponse;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.cartservice.business.dto.response.get.GetShoppingCartResponse;
import nurdanemin.cartservice.entities.CartItem;
import nurdanemin.cartservice.entities.ShoppingCart;
import nurdanemin.cartservice.repository.ShoppingCartRepository;
import nurdanemin.commonpackage.events.shoppingcart.ShoppingCartCreatedEvent;
import nurdanemin.commonpackage.utils.CommonMethods;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShoppingCartManager implements ShoppingCartService {
    private final ShoppingCartRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    private final CartItemService cartItemService;


    @Override
    public List<GetAllShoppingCartsResponse> getAll() {
       var carts = repository.findAll();
       return carts.stream().map(cart-> mapper.forResponse()
               .map(cart, GetAllShoppingCartsResponse.class)).toList();
    }

    @Override
    public GetShoppingCartResponse getById(UUID id) {
        var cart = repository.findById(id).orElseThrow();
        var response = mapForShopppingCartResponse(cart, new GetShoppingCartResponse());
        response.setCartItemIds(CommonMethods.getItemsAsUUIDSet(cart.getCartItems()));
        return response;
    }

    @Override
    public CreateShoppingCartResponse add(UUID userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(UUID.randomUUID());
        cart.setUserId(userId);
        var createdCart = repository.save(cart);
        ShoppingCartCreatedEvent event = new ShoppingCartCreatedEvent();
        event.setCartId(createdCart.getId());
        event.setUserId(userId);
        producer.sendMessage(event, "shopping-cart-for-user-created");
        return mapper.forResponse().map(createdCart, CreateShoppingCartResponse.class);
    }

    @Override
    public GetShoppingCartResponse addtoCart(UUID cartId, CreateCartItemRequest request) {
        ShoppingCart cart = repository.findById(cartId).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItemService.createCartItem(request, cart);
        cartItems.add(cartItem);
        var cartSaved = repository.save(cart);
        calculateTotalPrice(cartSaved);
        return getById(cartId);


    }

    @Override
    public GetShoppingCartResponse deleteItemFromCart(UUID shoppingCartId, UUID cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        ShoppingCart cart = repository.findById(shoppingCartId).orElseThrow();
        calculateTotalPrice(cart);
        return getById(shoppingCartId);
    }

    @Override
    public GetShoppingCartResponse updateItemQuantity(UUID shoppingCartId, UUID cartItemId, int quantity) {
        ShoppingCart cart = repository.findById(shoppingCartId).orElseThrow();
        cartItemService.updateQuantity(cartItemId, quantity);
        calculateTotalPrice(cart);
        return getById(shoppingCartId);
    }
    private void calculateTotalPrice(ShoppingCart cart){
        double sum = 0.0;
        for( CartItem item :cart.getCartItems() ){
            sum += item.getPricePerUnit()* item.getQuantity();
        }
        cart.setTotalPrice(sum);
        repository.save(cart);

    }

    public <T extends ShoppingCartResponseDto> T mapForShopppingCartResponse(ShoppingCart cart, T response){
        response.setId(cart.getId());
        response.setUserId(cart.getUserId());
        response.setTotalPrice(cart.getTotalPrice());
        return response;
    }



}
