package nurdanemin.filterservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.filterservice.business.abstracts.FilterService;
import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import nurdanemin.filterservice.entities.Filter;
import nurdanemin.filterservice.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        List<Filter>filters = repository.findAll();
        return filters
                .stream()
                .map(filter-> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();
    }

    @Override
    public GetFilterResponse getById(String id) {
       var filter = repository.findById(id);
        return mapper.forResponse().map(filter, GetFilterResponse.class);
    }

    @Override
    public void add(Filter filter) {
        repository.save(filter);

    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteByProductId(UUID productId) {
        repository.deleteByProductId(productId);

    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);

    }

    @Override
    public Filter getByCarId(UUID productId) {
       return repository.getByProductId(productId);
    }
}