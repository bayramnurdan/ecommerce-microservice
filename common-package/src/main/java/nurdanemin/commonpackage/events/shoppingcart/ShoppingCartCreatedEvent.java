package nurdanemin.commonpackage.events.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.events.Event;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartCreatedEvent implements Event {
    private UUID cartId;
    private UUID userId;
}
