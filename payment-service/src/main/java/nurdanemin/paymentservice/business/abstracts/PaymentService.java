package nurdanemin.paymentservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(Long id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    void delete(Long id);
    void processPayment(ProcessPaymentRequest request);
}
