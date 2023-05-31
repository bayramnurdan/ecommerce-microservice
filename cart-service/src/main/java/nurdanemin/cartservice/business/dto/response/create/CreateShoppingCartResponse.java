package nurdanemin.cartservice.business.dto.response.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShoppingCartResponse {
    private UUID id;
    private UUID userId;
}
