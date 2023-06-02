package nurdanemin.userservice.business.concretes;

import lombok.AllArgsConstructor;

import nurdanemin.commonpackage.events.user.UserCreatedEvent;
import nurdanemin.commonpackage.events.user.UserUpdatedEvent;
import nurdanemin.commonpackage.utils.CommonMethods;
import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.userservice.business.abstracts.AddressService;
import nurdanemin.userservice.business.abstracts.UserService;
import nurdanemin.userservice.business.dto.UserResponseDto;
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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor

public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapperService mapper;
    private final UserBusinessRules rules;
    private final AddressService addressService;
    private KafkaProducer producer;


    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> mapForUserResponse(user, new GetAllUsersResponse()))
                .toList();

    }

    @Override
    public GetUserResponse getById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        GetUserResponse response = mapForUserResponse(user, new GetUserResponse());
        response.setAddressesIds(CommonMethods.getItemsAsUUIDSet(user.getAddresses()));
        response.setOrderIds(user.getOrderIds());
        return response;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (producer==null){
            System.out.println("producer DELI");
        }
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
    public GetUserResponse addAddressForUser(CreateAddressRequest addressRequest) {
        rules.checkIfExistsById(addressRequest.getUserId());
        User user = repository.findById(addressRequest.getUserId()).orElseThrow();
        addressRequest.setUserId(user.getId());

        Address addressCreated = addressService.createAddress(addressRequest);
        Set<Address> usersAddresses = user.getAddresses();
        usersAddresses.add(addressCreated);

        repository.save(user);
        return getById(addressRequest.getUserId());

    }


    @Override
    public GetUserResponse deleteAddressFromUser(UUID addressId, UUID userId) {
       User user = repository.findById(userId).orElseThrow();
       addressService.deleteOwnerForAddress(user,addressId );
       var userAddresses = user.getAddresses();
       for (Address address: userAddresses){
           if (address.getId() == addressId){
               userAddresses.remove(address);
               break;
           }

       }
       user.setAddresses(userAddresses);
       repository.save(user);
       return getById(userId);
    }



    private <T extends UserResponseDto> T  mapForUserResponse(User user, T response){
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCartId(user.getCartId());
        return response;

    }


}
