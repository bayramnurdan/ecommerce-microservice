package nurdanemin.catalogservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.repository.BrandRepository;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;
    public void checkIfBrandExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException(Messages.Brand.NOT_EXIST);
        }
    }
    public void checkIfBrandExistByName(String name){
        if (repository.existsByNameIgnoreCase(name)){
            throw new BusinessException((Messages.Brand.ALREADY_EXISTS));
        }
    }
}