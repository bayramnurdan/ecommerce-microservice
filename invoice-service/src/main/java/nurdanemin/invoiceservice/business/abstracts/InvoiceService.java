package nurdanemin.invoiceservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.CreateInvoiceRequest;
import nurdanemin.invoiceservice.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.invoiceservice.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(Long id);
    Invoice add(CreateInvoiceRequest request);
}
