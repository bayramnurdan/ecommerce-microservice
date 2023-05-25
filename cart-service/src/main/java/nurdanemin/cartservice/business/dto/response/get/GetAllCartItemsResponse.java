package nurdanemin.cartservice.business.dto.response.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.cartservice.entities.ShoppingCart;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCartItemsResponse {
    private UUID id;

    private UUID productId;
    private String productName;
    private int productQuantity;
    private  double pricePerUnit;

    private UUID shoppingCartId;
}
