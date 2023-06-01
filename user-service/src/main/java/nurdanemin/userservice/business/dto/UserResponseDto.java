package nurdanemin.userservice.business.dto;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.userservice.entities.enums.Role;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @Enumerated
    private Role role;
    private UUID cartId;
}
