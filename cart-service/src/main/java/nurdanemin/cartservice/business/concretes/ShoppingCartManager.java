package nurdanemin.cartservice.business.concretes;

import lombok.RequiredArgsConstructor;
import nurdanemin.cartservice.business.abstracts.ShoppingCartService;
import nurdanemin.cartservice.business.dto.response.create.CreateShoppingCartResponse;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.cartservice.business.dto.response.get.GetShoppingCartResponse;
import nurdanemin.cartservice.entities.ShoppingCart;
import nurdanemin.cartservice.repository.ShoppingCartRepository;
import nurdanemin.commonpackage.events.shoppingcart.ShoppingCartCreatedEvent;
import nurdanemin.commonpackage.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartManager implements ShoppingCartService {
    private final ShoppingCartRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
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
        producer.sendMessage(new ShoppingCartCreatedEvent(createdCart.getId(), userId), "shopping-cart-created");
        return mapper.forResponse().map(createdCart, CreateShoppingCartResponse.class);

    }




}
