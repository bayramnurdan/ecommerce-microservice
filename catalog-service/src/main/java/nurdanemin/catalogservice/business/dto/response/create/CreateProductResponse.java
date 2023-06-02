package nurdanemin.catalogservice.business.dto.response.create;

import jakarta.persistence.ElementCollection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductResponse {
    private UUID id;
    private String name;
    private int amount;
    private double price;
    private double discount;

    private UUID brandId;
    @ElementCollection
    private Set<UUID> categoryIds;

}
