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
public class GetShoppingCartResponse  extends ShoppingCartResponseDto {
    private String userFirstName;
    private String userLastName;

    @ElementCollection
    private Set<UUID> cartItemIds = new HashSet<>();
}
