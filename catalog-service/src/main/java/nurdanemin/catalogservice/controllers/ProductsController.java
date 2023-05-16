package nurdanemin.catalogservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.business.abstracts.ProductService;
import nurdanemin.catalogservice.business.dto.request.create.CreateProductRequest;
import nurdanemin.catalogservice.business.dto.request.update.UpdateProductRequest;
import nurdanemin.catalogservice.business.dto.response.create.CreateProductResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetAllProductsResponse;
import nurdanemin.catalogservice.business.dto.response.get.GetProductResponse;
import nurdanemin.catalogservice.business.dto.response.update.UpdateProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService service;
    @GetMapping
    public List<GetAllProductsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public GetProductResponse getById(@PathVariable UUID id){
        return service.getById(id);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse create(@Valid @RequestBody CreateProductRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public UpdateProductResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateProductRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(UUID id){
        service.delete(id);
    }
}
