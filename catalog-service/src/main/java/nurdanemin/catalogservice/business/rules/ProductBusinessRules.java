package nurdanemin.catalogservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.repository.ProductRepository;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;
    public void checkIfProductExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException(("PRODUCT_DOES_NOT_EXIST"));
        }
    }
    public void checkIfProductExistByNameAndBrand(String name, UUID brandId){
        if (repository.existsByNameIgnoreCaseAndAndBrandId(name, brandId)){
            throw new BusinessException(("PRODUCT_ALREADY_EXISTS"));
        }
    }
}
