package nurdanemin.filterservice.business.abstracts;

import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import nurdanemin.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();

    GetFilterResponse getById(String id);

    void add(Filter filter);

    void delete(String id);

    void deleteByProductId(UUID productId);


    void deleteAllByBrandId(UUID brandId);

    Filter getByCarId(UUID carId);
}
