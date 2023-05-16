package nurdanemin.catalogservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.BrandService;
import nurdanemin.catalogservice.business.dto.request.create.CreateBrandRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateBrandRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateBrandResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllBrandsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetBrandResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateBrandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;
    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public GetBrandResponse getById(@PathVariable  UUID id){
        return service.getById(id);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse create(@Valid @RequestBody  CreateBrandRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public UpdateBrandResponse update(@PathVariable UUID id, @Valid @RequestBody  UpdateBrandRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(UUID id){
        service.delete(id);
    }
}
