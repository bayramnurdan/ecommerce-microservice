package nurdanemin.shippingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shippings")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String receiversFirstName;
    private String receiversLastName;
    private UUID addressId;
    private LocalDateTime createdAt;
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;


}