package nurdanemin.userservice.business.dto.request.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @Size(min = 3, max = 15)
    private String firstName;
    @Size(min = 3, max = 15)
    private String lastName;
    @Email
    private String email;
}