package nurdanemin.cartservice.repository;

import nurdanemin.cartservice.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
