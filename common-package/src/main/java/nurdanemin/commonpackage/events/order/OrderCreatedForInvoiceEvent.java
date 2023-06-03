package nurdanemin.commonpackage.events.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.events.Event;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreatedForInvoiceEvent implements Event {
    private String customerFirstName;
    private String customerLastName;
    private double totalAmount;
    private String cardHolder;
    private LocalDateTime orderedAt;
}
