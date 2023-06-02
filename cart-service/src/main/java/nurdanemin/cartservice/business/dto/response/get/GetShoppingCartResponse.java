package nurdanemin.cartservice.business.dto.response.get;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.cartservice.business.dto.response.ShoppingCartResponseDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetShoppingCartResponse  extends ShoppingCartResponseDto {



    @ElementCollection
    private Set<UUID> cartItemIds = new HashSet<>();
}
