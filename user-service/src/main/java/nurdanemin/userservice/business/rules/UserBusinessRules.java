package nurdanemin.userservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;
    public void checkIfEmailUsedBefore(String email){
        if (repository.existsByEmail(email)){
            throw new BusinessException(Messages.User.EmailIsUsedBefore);
        }
    }

    public void checkIfExistsById(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException(Messages.User.NotExists);
        }
    }

}