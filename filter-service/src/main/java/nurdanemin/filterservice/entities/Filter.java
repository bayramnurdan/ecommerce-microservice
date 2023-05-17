package nurdanemin.filterservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Filter {
    @Id
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
