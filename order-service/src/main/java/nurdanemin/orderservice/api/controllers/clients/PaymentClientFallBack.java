package nurdanemin.orderservice.api.controllers.clients;

import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;

public class PaymentClientFallBack implements PaymentClient{
    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        return null;
    }

    @Override
    public void processPayment(ProcessPaymentRequest request) {

    }
}
