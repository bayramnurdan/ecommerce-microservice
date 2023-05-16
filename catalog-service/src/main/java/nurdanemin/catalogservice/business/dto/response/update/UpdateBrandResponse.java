package nurdanemin.catalogservice.business.dto.response.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBrandResponse {
    private UUID id;
    private String name;
}
