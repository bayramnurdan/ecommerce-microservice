package nurdanemin.paymentservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.paymentservice.business.abstracts.PaymentService;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import nurdanemin.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return null;
    }

    @Override
    public GetPaymentResponse getById(Long id) {
        return null;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void processPayment(ProcessPaymentRequest request) {

    }
}
