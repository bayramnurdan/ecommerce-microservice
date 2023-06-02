package nurdanemin.orderservice.api.controllers.clients;

import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("api/payments")
    public CreatePaymentResponse add(CreatePaymentRequest request);


    @PostMapping("api/payments/process-payment")
    public void processPayment(ProcessPaymentRequest request);

}
