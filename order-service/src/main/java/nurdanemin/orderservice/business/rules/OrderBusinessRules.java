package nurdanemin.orderservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderBusinessRules {
    private final OrderRepository repository;

}