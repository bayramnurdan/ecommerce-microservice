package nurdanemin.invoiceservice.repository;

import nurdanemin.invoiceservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}

