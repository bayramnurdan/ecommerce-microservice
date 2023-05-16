package nurdanemin.catalogservice.business.dto.response.update;

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
public class UpdateProductResponse {
    private UUID id;
    private String name;
    private int amount;
    private double price;
    private double discount;

    private UUID brandId;

    private List<UUID> categoryIds;
}
