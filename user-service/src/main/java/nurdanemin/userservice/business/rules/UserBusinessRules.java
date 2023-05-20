package nurdanemin.userservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;
    public void checkIfEmailExists(String email){
        if (repository.existsByEmail(email)){
            throw new BusinessException("EMAIL_ALREADY_EXISTS");
        }
    }

    public void checkIfExistsById(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("NOT_EXISTS_BY_ID");
        }
    }
}
