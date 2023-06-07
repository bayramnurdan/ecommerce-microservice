package nurdanemin.catalogservice.business.dto.request.create;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBrandRequest {
    @Size(max = 10, min = 2)
    private String name;
}