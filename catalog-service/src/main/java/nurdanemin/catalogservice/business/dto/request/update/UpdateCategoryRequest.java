package nurdanemin.catalogservice.business.dto.request.update;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryRequest {
    @Size(max = 10, min = 2)
    private String name;
}