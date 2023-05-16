package nurdanemin.catalogservice.business.abstracts;

import nurdanemin.catalogservice.business.dto.request.create.CreateBrandRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateBrandRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateBrandResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllBrandsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetBrandResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(UUID id);
    CreateBrandResponse create(CreateBrandRequest request);
    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);
    void delete(UUID id);

}
