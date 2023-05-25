package nurdanemin.cartservice.business.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCartItemRequest {
    private UUID productId;
    private UUID cartId;

    private int quantity;
    private double discount;
    private double price = 0.0 ;
}
