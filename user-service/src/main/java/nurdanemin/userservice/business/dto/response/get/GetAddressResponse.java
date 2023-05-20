package nurdanemin.userservice.business.dto.response.get;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAddressResponse {
    private UUID id;
    private int apartmentNumber;
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;
    @ElementCollection
    private List<UUID> ownerIds;
}
