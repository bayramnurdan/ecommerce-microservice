package nurdanemin.filterservice.business.dto.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GetFilterResponse {
    private String id;
    private UUID productId;
    private String productName;
    private UUID brandId;
    private String brandName;
    private int amount;
    private double price;
    private double discount;
    private List<UUID> categoryIds;
    private List<String> categoryNames;
}
