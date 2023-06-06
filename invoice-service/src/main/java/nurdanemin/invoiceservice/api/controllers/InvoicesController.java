package nurdanemin.invoiceservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import nurdanemin.invoiceservice.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetInvoiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/invoices")
@AllArgsConstructor
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
}