package nurdanemin.userservice.business.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAddressRequest {
    @Min(1)
    private int apartmentNumber;

    @Size(min = 3, max = 15)
    private String building;

    @Size(min = 3, max = 15)
    private String street;

    @Size(min = 3, max = 15)
    private String district;

    @Size(min = 3, max = 15)
    private String city;

    private String country = "Türkiye";
    private UUID userId = null;
}