package nurdanemin.userservice.business.abstracts;

import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.response.get.GetAddressResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllAddressesResponse;
import nurdanemin.userservice.entities.Address;

import java.util.List;

public interface AddressService {
    List<GetAllAddressesResponse> getAll();

    GetAddressResponse getById(Long id);


    Address createAddress(CreateAddressRequest request);


    void addUserForAddress(Address address, User user);
    void updateOwnersOfAddress(Long addressId, User user);
}
