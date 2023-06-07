package nurdanemin.paymentservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.paymentservice.entities.Payment;
import nurdanemin.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID paymentId){
        if (!repository.existsById(paymentId)){
            throw new BusinessException(Messages.Payment.NOT_EXIST);
        }
    }
    public void checkIfBalanceEnough(ProcessPaymentRequest request){
        Payment payment = repository.findById(request.getPaymentId()).orElseThrow();
        if (payment.getBalance()<request.getAmount()){
            throw new BusinessException(Messages.Payment.NOT_ENOUGH);
        }
    }
}