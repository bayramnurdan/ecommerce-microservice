package nurdanemin.shippingservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.shippingservice.business.abstracts.ShippingService;
import nurdanemin.shippingservice.business.dto.request.update.UpdateShippingRequest;
import nurdanemin.shippingservice.business.dto.response.get.GetAllShippingsResponse;
import nurdanemin.shippingservice.business.dto.response.get.GetShippingResponse;
import nurdanemin.shippingservice.business.dto.response.update.UpdateShippingResponse;
import nurdanemin.shippingservice.entities.ShippingStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/shippings")
public class ShippingsController {
    private final ShippingService service;
    @GetMapping
    public List<GetAllShippingsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    GetShippingResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PutMapping("{id}")
    public UpdateShippingResponse updateShipping(@PathVariable UUID id, @Valid @RequestBody UpdateShippingRequest request){
        return service.updateShipping(id, request);
    }

    @PutMapping("/update-status/{id}")
    public GetShippingResponse updateShippingStatus(@PathVariable UUID id, @RequestParam ShippingStatus status){
        return service.updateShippingStatus(id, status);
    }

}