package nurdanemin.invoiceservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.order.OrderCreatedForInvoiceEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import nurdanemin.invoiceservice.business.dto.response.create.CreateInvoiceResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.invoiceservice.entity.Invoice;
import nurdanemin.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private InvoiceRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();
    }

    @Override
    public GetInvoiceResponse getById(UUID id) {
        return mapper.forResponse().map(repository.findById(id).orElseThrow(), GetInvoiceResponse.class);
    }

    @Override
    public CreateInvoiceResponse add(OrderCreatedForInvoiceEvent event) {
       Invoice invoice = mapper.forRequest().map(event, Invoice.class);
       invoice.setId(UUID.randomUUID());
       var savedInvoice = repository.save(invoice);
       return mapper.forResponse().map(savedInvoice,CreateInvoiceResponse.class );
    }
}
