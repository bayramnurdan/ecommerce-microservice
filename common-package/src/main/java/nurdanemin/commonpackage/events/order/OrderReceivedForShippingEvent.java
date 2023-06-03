package nurdanemin.commonpackage.events.order;

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
public class OrderReceivedForShippingEvent implements Event {
    private String receiversFirstName;
    private String receiversLastName;
    private UUID addressId;
    private UUID orderId;
}
