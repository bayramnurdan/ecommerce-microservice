package nurdanemin.userservice.business.abstracts;

import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.response.get.GetAddressResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllAddressesResponse;
import nurdanemin.userservice.entities.Address;
import nurdanemin.userservice.entities.User;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    List<GetAllAddressesResponse> getAll();

    GetAddressResponse getById(UUID id);


    Address createAddress(CreateAddressRequest request);


    void addUserForAddress(Address address, User user);
    void deleteOwnerOfAddress(UUID addressId, UUID userId);
}
