package nurdanemin.commonpackage.utils.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetShoppingCartResponse {
    private UUID id;
    private UUID userId;
    private String userFirstName;
    private String userLastName;
    private double totalPrice;


    @ElementCollection
    private Set<UUID> cartItemIds = new HashSet<>();
}