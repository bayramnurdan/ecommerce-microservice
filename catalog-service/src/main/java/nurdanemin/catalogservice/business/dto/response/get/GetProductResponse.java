package nurdanemin.catalogservice.business.dto.response.get;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
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

    private UUID brandId;

    private List<UUID> categoryIds;
}
