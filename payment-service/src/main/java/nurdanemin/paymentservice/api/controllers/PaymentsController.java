package nurdanemin.paymentservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.dto.CreatePaymentRequest;
import nurdanemin.commonpackage.utils.dto.ProcessPaymentRequest;
import nurdanemin.paymentservice.business.abstracts.PaymentService;
import nurdanemin.commonpackage.utils.dto.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/payments")
public class PaymentsController {
    private final PaymentService service;
    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("api/{id}")
    public GetPaymentResponse getById(UUID id){
        return service.getById(id);
    }
    @PostMapping
    public CreatePaymentResponse add(CreatePaymentRequest request){
        return service.add(request);
    }
    @PostMapping("/process-payment")

    public void processPayment(ProcessPaymentRequest request){
        service.processPayment(request);
    }
}

