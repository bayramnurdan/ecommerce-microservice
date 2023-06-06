package nurdanemin.cartservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.cartservice.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemBusinessRules {
    private final CartItemRepository repository;
}