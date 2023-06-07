package nurdanemin.filterservice.repository;

import nurdanemin.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, String> {
    Filter getByProductId(UUID id);
    List<Filter> getAllByBrandNameIgnoreCase(String brandName);
}