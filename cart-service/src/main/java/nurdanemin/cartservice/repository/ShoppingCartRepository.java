package nurdanemin.cartservice.repository;

import nurdanemin.cartservice.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
}
