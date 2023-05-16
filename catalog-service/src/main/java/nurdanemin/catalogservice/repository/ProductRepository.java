package nurdanemin.catalogservice.repository;

import nurdanemin.catalogservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameIgnoreCaseAndAndBrandId(String name, UUID brandId);
}
