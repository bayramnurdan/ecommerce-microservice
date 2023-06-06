package nurdanemin.shippingservice.business.dto.response.update;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.shippingservice.entities.ShippingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateShippingResponse {
    private UUID id;
    private String receiversFirstName;
    private String receiversLastName;
    private UUID addressId;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;
}