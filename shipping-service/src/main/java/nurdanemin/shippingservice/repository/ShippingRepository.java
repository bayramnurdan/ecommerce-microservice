package nurdanemin.shippingservice.repository;

import nurdanemin.shippingservice.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
}
