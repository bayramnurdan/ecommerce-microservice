package nurdanemin.cartservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.cartservice.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppingCartBusinessRules {
    private final ShoppingCartRepository repository;

}