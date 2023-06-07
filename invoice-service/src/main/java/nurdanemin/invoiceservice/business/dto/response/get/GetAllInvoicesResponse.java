package nurdanemin.invoiceservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllInvoicesResponse {
    private UUID id;

    private String customerFirstName;
    private String customerLastName;

    private LocalDateTime OrderedAt;
    private double totalAmount;
}