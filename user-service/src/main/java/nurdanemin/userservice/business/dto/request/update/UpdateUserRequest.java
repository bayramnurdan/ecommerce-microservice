package nurdanemin.userservice.business.dto.request.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {
    @Size(min = 3, max = 15)
    private String firstName;
    @Size(min = 3, max = 15)
    private String lastName;
    @Email
    private String email;
}