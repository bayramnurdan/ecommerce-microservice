package nurdanemin.cartservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllShoppingCartsResponse {
    private UUID id;
    private Long userId;
    private double totalPrice;
}
