package nurdanemin.cartservice.business.concretes;

import lombok.RequiredArgsConstructor;
import nurdanemin.cartservice.api.clients.ProductClient;
import nurdanemin.cartservice.business.abstracts.CartItemService;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.cartservice.business.dto.request.create.CreateCartItemRequest;
import nurdanemin.cartservice.business.dto.response.create.CreateShoppingCartResponse;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.cartservice.business.dto.response.get.GetShoppingCartResponse;
import nurdanemin.cartservice.entities.CartItem;
import nurdanemin.cartservice.entities.ShoppingCart;
import nurdanemin.cartservice.repository.ShoppingCartRepository;
import nurdanemin.commonpackage.events.shoppingcart.ShoppingCartCreatedEvent;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartManager implements ShoppingCartService {
    private final ShoppingCartRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    private final CartItemService cartItemService;
    private final ProductClient productClient;

    @Override
    public List<GetAllShoppingCartsResponse> getAll() {
       var carts = repository.findAll();
       return carts.stream().map(cart-> mapper.forResponse()
               .map(cart, GetAllShoppingCartsResponse.class)).toList();
    }

    @Override
    public GetShoppingCartResponse getById(UUID id) {
        var cart = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(cart, GetShoppingCartResponse.class);
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
    public GetShoppingCartResponse addtoCart(CreateCartItemRequest request) {
        ProductClientResponse response = productClient.getProductPrice(request.getProductId());
        if (response.isSuccess()){
            request.setPrice(response.getProductPrice());
        }
        ShoppingCart cart = repository.findById(request.getCartId()).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItemService.createCartItem(request);
        cartItems.add(cartItem);
        var cartSaved = repository.save(cart);
        calculateTotalPrice(cartSaved);
        cartItemService.setCartForCartItem(cartItem, cartSaved);
        return getById(request.getCartId());

    }

    @Override
    public GetShoppingCartResponse deleteItemFromCart(UUID shoppingCartId, UUID cartItemId) {
        ShoppingCart cart = repository.findById(shoppingCartId).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(cartItem ->(cartItem.getId() == cartItemId));
        var savedCart = repository.save(cart);
        calculateTotalPrice(savedCart);
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
            sum += item.getPricePerUnit()* item.getProductQuantity();
        }
        cart.setTotalPrice(sum);
        repository.save(cart);

    }


}
