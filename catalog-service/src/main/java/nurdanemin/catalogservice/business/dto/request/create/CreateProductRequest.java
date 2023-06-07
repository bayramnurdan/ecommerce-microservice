package nurdanemin.catalogservice.business.dto.request.create;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
public class CreateProductRequest {
    @Size(max = 20, min = 2)
    private String name;
    @Min(1)
    private int amount;
    @Min(1)
    private double price;
    @Min(0)
    private double discount;

    private UUID brandId;
    @ElementCollection
    private Set<UUID> categoryIds;
}