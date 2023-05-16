package nurdanemin.catalogservice.business.dto.request.create;

import jakarta.persistence.ElementCollection;
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
public class CreateProductRequest {
    private String name;
    private int amount;
    private double price;
    private double discount;

    private UUID brandId;
    @ElementCollection
    private List<UUID> categoryIds;
}
