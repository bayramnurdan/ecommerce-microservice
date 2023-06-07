package nurdanemin.userservice.business.abstracts;

import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.request.create.CreateUserRequest;
import nurdanemin.userservice.business.dto.request.update.UpdateUserRequest;
import nurdanemin.userservice.business.dto.response.create.CreateUserResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllUsersResponse;
import nurdanemin.userservice.business.dto.response.get.GetUserResponse;
import nurdanemin.userservice.business.dto.response.update.UpdateUserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    GetUserResponse getById(UUID id);
    GetUserResponse getByCartId(UUID cartId);
    CreateUserResponse createUser(CreateUserRequest request);
    UpdateUserResponse updateUser(UUID id, UpdateUserRequest request);
    void addCartForUser(UUID userId, UUID cartId);
    GetUserResponse addAddressForUser(CreateAddressRequest addressRequest);
    GetUserResponse deleteAddressFromUser( UUID userId, UUID addressId);
}