package nurdanemin.paymentservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    void processPayment(ProcessPaymentRequest request);
}
