package nurdanemin.catalogservice.business.dto.response.get;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.utils.enums.ProductState;


import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetProductResponse {
    private UUID id;
    private String name;
    private int amount;
    private double price;
    private double discount;
    @Enumerated(EnumType.STRING)
    private ProductState state;

    private UUID brandId;

    private Set<UUID> categoryIds;
}