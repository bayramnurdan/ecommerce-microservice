package nurdanemin.catalogservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.CategoryService;
import nurdanemin.catalogservice.business.dto.request.create.CreateCategoryRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateCategoryRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllCategoriesResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetCategoryResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService service;
    @GetMapping
    public List<GetAllCategoriesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public GetCategoryResponse getById(@PathVariable UUID id){
        return service.getById(id);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse create(@Valid @RequestBody CreateCategoryRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public UpdateCategoryResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(UUID id){
        service.delete(id);
    }
    
}
