package nurdanemin.filterservice.repository;

import nurdanemin.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, String> {
    void deleteByProductId(UUID productId);
    void deleteAllByBrandId(UUID brandId);
    Filter getByProductId(UUID id);
}
