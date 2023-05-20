package nurdanemin.commonpackage.events.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nurdanemin.commonpackage.events.Event;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ShoppingCartCreatedEvent implements Event {
    private UUID cartId;
    private UUID userId;
}
