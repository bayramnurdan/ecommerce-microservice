package nurdanemin.userservice.business.dto.response.get;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.userservice.entities.Address;
import nurdanemin.userservice.entities.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetUserResponse {
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @Enumerated
    private Role role;


    @ElementCollection
    private List<UUID> addressesIds = new ArrayList<>();


    private Long cartId;

    @ElementCollection
    private List<Long> orderIds;
}
