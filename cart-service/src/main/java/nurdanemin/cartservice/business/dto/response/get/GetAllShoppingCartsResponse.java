package nurdanemin.cartservice.business.dto.response.get;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllShoppingCartsResponse {
    private UUID id;
    private UUID userId;
    private double totalPrice;
    private Set<UUID> cartItemIds;


}