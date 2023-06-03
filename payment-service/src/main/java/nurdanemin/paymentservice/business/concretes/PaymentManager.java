package nurdanemin.paymentservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.paymentservice.business.abstracts.PaymentService;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import nurdanemin.paymentservice.entities.Payment;
import nurdanemin.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return repository.findAll()
                .stream().map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class))
                .toList();
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        return mapper.forResponse().map(repository.findById(id).orElseThrow(), GetPaymentResponse.class);

    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        var payment = mapper.forRequest().map(request, Payment.class);
        var savedPayment = repository.save(payment);
        return mapper.forResponse().map(savedPayment, CreatePaymentResponse.class);

    }


    @Override
    public void processPayment(ProcessPaymentRequest request) {
        // TODO : BUSİNESS RULES
        Payment payment = repository.findById(request.getPaymentId()).orElseThrow();
        payment.setBalance(payment.getBalance()- request.getAmount());
        repository.save(payment);

    }
}
