package nurdanemin.cartservice.business.dto.response.get;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.cartservice.entities.CartItem;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetShoppingCartResponse {
    private UUID id;
    private Long userId;
    private double totalPrice;
    @ElementCollection
    private Set<UUID> cartItemIds = new HashSet<>();
}
