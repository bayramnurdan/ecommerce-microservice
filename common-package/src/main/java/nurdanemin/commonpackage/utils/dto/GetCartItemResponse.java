package nurdanemin.commonpackage.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCartItemResponse {
    private UUID id;

    private UUID productId;
    private String productName;
    private int productQuantity;
    private  double pricePerUnit;

    private UUID shoppingCartId;
}
