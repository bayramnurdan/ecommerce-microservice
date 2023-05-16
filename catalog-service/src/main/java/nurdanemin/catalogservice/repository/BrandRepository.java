package nurdanemin.catalogservice.repository;

import nurdanemin.catalogservice.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
