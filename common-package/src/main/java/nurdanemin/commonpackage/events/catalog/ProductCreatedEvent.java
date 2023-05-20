package nurdanemin.commonpackage.events.catalog;

import lombok.*;
import nurdanemin.commonpackage.events.Event;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCreatedEvent implements Event {
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
