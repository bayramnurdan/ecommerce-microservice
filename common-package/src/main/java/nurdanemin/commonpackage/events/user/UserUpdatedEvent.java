package nurdanemin.commonpackage.events.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.events.Event;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdatedEvent implements Event {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
}
