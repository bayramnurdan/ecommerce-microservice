package nurdanemin.invoiceservice.business.abstracts;

import nurdanemin.commonpackage.events.order.OrderCreatedForInvoiceEvent;
import nurdanemin.invoiceservice.business.dto.response.create.CreateInvoiceResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoiceResponse add(OrderCreatedForInvoiceEvent event);
}
