package nurdanemin.filterservice.business.abstracts;

import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import nurdanemin.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();

    GetFilterResponse getByProductId(UUID id);

    void add(Filter filter);
    List<GetAllFiltersResponse> getByBrandName(String brandName);



}