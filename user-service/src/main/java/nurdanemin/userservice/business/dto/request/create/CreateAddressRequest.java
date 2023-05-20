package nurdanemin.userservice.business.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAddressRequest {
    @NotBlank
    private int apartmentNumber;
    @NotBlank
    private String building;
    @NotBlank
    private String street;
    @NotBlank
    private String district;
    @NotBlank
    private String city;
}
