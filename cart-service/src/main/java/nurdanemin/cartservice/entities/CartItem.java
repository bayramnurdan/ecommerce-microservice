package nurdanemin.cartservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="CartItems")
public class CartItem implements nurdanemin.commonpackage.events.Id {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID productId;
    private String productName;
    private int quantity;
    private  double pricePerUnit;


    @ManyToOne
    @JsonIgnore
    private ShoppingCart cart;
}
