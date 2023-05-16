package nurdanemin.catalogservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.BrandService;
import nurdanemin.catalogservice.business.dto.request.create.CreateBrandRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateBrandRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateBrandResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllBrandsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetBrandResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateBrandResponse;
import nurdanemin.catalogservice.business.rules.BrandBusinessRules;
import nurdanemin.catalogservice.entities.Brand;
import nurdanemin.catalogservice.repository.BrandRepository;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapperService mapper;
    private final BrandBusinessRules rules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        var brands = repository.findAll();
        var response = brands
                .stream()
                .map(brand-> mapper.forResponse().map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;

    }

    @Override
    public GetBrandResponse getById(UUID id) {
        rules.checkIfBrandExists(id);
        var brand = repository.findById(id);
        var response = mapper.forResponse().map(brand, GetBrandResponse.class);
        return response;
    }

    @Override
    public CreateBrandResponse create(CreateBrandRequest request) {
        rules.checkIfBrandExistByName(request.getName());
        var brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(UUID.randomUUID());
        var createdBrand = repository.save(brand);
        var response = mapper.forResponse().map(createdBrand, CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        rules.checkIfBrandExists(id);
        var brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(id);
        var createdBrand = repository.save(brand);
        var response = mapper.forResponse().map(createdBrand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfBrandExists(id);
        repository.deleteById(id);

    }
}
