package nurdanemin.userservice.business.concretes;

import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.events.user.UserCreatedEvent;
import nurdanemin.commonpackage.events.user.UserUpdatedEvent;
import nurdanemin.commonpackage.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.userservice.business.abstracts.UserService;
import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.request.create.CreateUserRequest;
import nurdanemin.userservice.business.dto.request.update.UpdateUserRequest;
import nurdanemin.userservice.business.dto.response.create.CreateUserResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllUsersResponse;
import nurdanemin.userservice.business.dto.response.get.GetUserResponse;
import nurdanemin.userservice.business.dto.response.update.UpdateUserResponse;
import nurdanemin.userservice.business.rules.UserBusinessRules;
import nurdanemin.userservice.entities.Address;
import nurdanemin.userservice.entities.User;
import nurdanemin.userservice.entities.enums.Role;
import nurdanemin.userservice.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapperService mapper;
    private final UserBusinessRules rules;
    private KafkaProducer producer;


    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> mapper.forResponse().map(user, GetAllUsersResponse.class))
                .toList();

    }

    @Override
    public GetUserResponse getById(UUID id) {
        User user = repository.findById(id).orElseThrow();



        return null;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        rules.checkIfEmailExists(request.getEmail());
        User user = mapper.forRequest().map(request, User.class);
        user.setId(UUID.randomUUID());
        user.setRole(Role.USER);
        User createdUser = repository.save(user);
        producer.sendMessage(new UserCreatedEvent(createdUser.getId()), "user-created");
        return mapper.forResponse().map(createdUser, CreateUserResponse.class);
    }

    @Override
    public UpdateUserResponse updateUser(UUID id, UpdateUserRequest request) {
        rules.checkIfEmailExists(request.getEmail());
        User user = mapper.forRequest().map(request, User.class);
        user.setId(id);
        User updatedUser = repository.save(user);
        producer.sendMessage(mapper.forResponse().map(updatedUser, UserUpdatedEvent.class), "user-updated");
        return mapper.forResponse().map(updatedUser, UpdateUserResponse.class);
    }

    @Override
    public void addCartForUser(UUID userId, UUID cartId) {
        User user = repository.findById(userId).orElseThrow();
        user.setCartId(cartId);
        repository.save(user);
    }

    @Override
    public void addAddressForUser(UUID userId, CreateAddressRequest addressRequest) {
        rules.checkIfExistsById(userId);
        User user = repository.findById(userId).orElseThrow();

        Address addressCreated = addressService.createAddress(addressRequest);
        List<Address> usersAddresses = user.getAddresses();
        usersAddresses.add(addressCreated);

        User userUpdated = repository.save(user);

    }

    @Override
    public void deleteAddressFromUser(Long addressId, Long userId) {

    }


}
