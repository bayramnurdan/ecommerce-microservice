package nurdanemin.userservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllAddressesResponse {
    private UUID id;
    private int apartmentNumber;
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;

}
