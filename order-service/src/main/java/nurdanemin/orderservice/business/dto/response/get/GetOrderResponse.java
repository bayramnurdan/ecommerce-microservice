package nurdanemin.orderservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.orderservice.entities.OrderStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOrderResponse {
    private UUID id;

    private UUID cartId;
    private double totalOrderPrice;
    private OrderStatus status;

    private Set<UUID> orderItems = new HashSet<>();
    private LocalDateTime orderedAt;
}
