package nurdanemin.cartservice.business.abstracts;

import nurdanemin.cartservice.business.dto.response.create.CreateShoppingCartResponse;
import nurdanemin.cartservice.business.dto.response.get.GetAllShoppingCartsResponse;
import nurdanemin.cartservice.business.dto.response.get.GetShoppingCartResponse;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<GetAllShoppingCartsResponse> getAll();
    GetShoppingCartResponse getById(UUID id);
    CreateShoppingCartResponse add(UUID userId);


}
