package nurdanemin.commonpackage.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShippingRequest {
    private String receiversFirstName;
    private String receiversLastName;
    private UUID addressId;
}
