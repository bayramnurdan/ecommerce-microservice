package nurdanemin.commonpackage.events.catalog;

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
public class ProductQuantityUpdatedEvent  implements Event {
    private UUID productId;
    private int quantity;

}