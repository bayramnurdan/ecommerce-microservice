package nurdanemin.commonpackage.utils.dto;

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
public class CreateShippingResponse {
    private UUID id;
    private String receiversFirstName;
    private String receiversLastName;
    private UUID addressId;
    private LocalDateTime createdAt;
}
