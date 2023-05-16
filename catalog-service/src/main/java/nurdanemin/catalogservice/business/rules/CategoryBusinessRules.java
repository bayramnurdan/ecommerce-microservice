package nurdanemin.catalogservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.catalogservice.repository.CategoryRepository;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository repository;
    public void checkIfCategoryExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException(("CATEGORY_DOES_NOT_EXIST"));
        }
    }
   public  void checkIfCategoryExistByName(String name){
        if (repository.existsByNameIgnoreCase(name)){
            throw new BusinessException(("CATEGORY_ALREADY_EXISTS"));
        }
    }
}
