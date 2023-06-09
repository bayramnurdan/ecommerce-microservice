package nurdanemin.orderservice.business.dto.response.create;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import nurdanemin.orderservice.entities.OrderStatus;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderResponse {
    private UUID id;
    private UUID cartId;
    private double totalOrderPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Set<UUID> itemIds;
    private LocalDateTime orderedAt;
}