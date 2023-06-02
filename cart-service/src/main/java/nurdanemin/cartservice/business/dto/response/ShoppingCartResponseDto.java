package nurdanemin.cartservice.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartResponseDto {
    private UUID id;
    private UUID userId;
    private double totalPrice;
}
