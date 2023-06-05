package nurdanemin.filterservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.filterservice.business.abstracts.FilterService;
import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/filters")
public class FiltersController {
    private final FilterService service;

    @GetMapping
    public List<GetAllFiltersResponse> getAll(){return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable String id) {
        return service.getById(id);
    }
}