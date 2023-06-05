package nurdanemin.orderservice.business.dto.response.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.orderservice.entities.Order;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllOrderItemsResponse {
    private UUID id;
    private UUID productId;
    private String productName;
    private int productQuantity;
    private double totalPrice;
    private  double pricePerUnit;
    private UUID orderId;
}