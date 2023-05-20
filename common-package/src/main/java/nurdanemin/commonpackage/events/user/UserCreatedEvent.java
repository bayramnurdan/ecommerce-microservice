package nurdanemin.commonpackage.events.user;

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
public class UserCreatedEvent  implements Event {
    private UUID userId;
}
