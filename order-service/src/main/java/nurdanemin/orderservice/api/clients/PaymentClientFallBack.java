package nurdanemin.orderservice.api.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallBack implements PaymentClient{
    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        return null;
    }

    @Override
    public void processPayment(ProcessPaymentRequest request) {

    }
}