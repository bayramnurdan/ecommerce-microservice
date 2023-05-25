package nurdanemin.userservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.userservice.business.abstracts.AddressService;
import nurdanemin.userservice.business.dto.response.get.GetAddressResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllAddressesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/addresses")
public class AddressesController {
    private final AddressService service;


    @GetMapping
    public List<GetAllAddressesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAddressResponse getById(@PathVariable UUID id) {
        return service.getById(id);

    }

}
