package nurdanemin.userservice.business.dto.response.get;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.userservice.business.dto.UserResponseDto;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetUserResponse extends UserResponseDto {
    @ElementCollection
    private Set<UUID> addressesIds = new HashSet<>();
    @ElementCollection
    private Set<UUID> orderIds;
}
