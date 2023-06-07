package nurdanemin.commonpackage.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.utils.enums.ProductState;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductClientResponse extends ClientResponse{
    private double productPrice;
    private String productName;
    private ProductState productState;

}