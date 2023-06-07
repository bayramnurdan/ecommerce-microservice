package nurdanemin.userservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.userservice.business.abstracts.AddressService;
import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.response.get.GetAddressResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllAddressesResponse;
import nurdanemin.userservice.business.rules.AddressBusinessRules;
import nurdanemin.userservice.entities.Address;
import nurdanemin.userservice.entities.User;
import nurdanemin.userservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository repository;
    private final ModelMapperService mapper;
    private final AddressBusinessRules rules;
    @Override
    public List<GetAllAddressesResponse> getAll() {
        var addresses = repository.findAll();
        return addresses
                .stream()
                .map(address -> (mapper.forResponse().map(address, GetAllAddressesResponse.class)))
                .toList();
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        rules.checkIfExistsById(id);
        var address = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(address, GetAddressResponse.class);
    }

    @Override
    public Address createAddress(CreateAddressRequest request) {
        if (rules.checkIfAddressExists(request.getApartmentNumber(),
                request.getBuilding(), request.getStreet(),
                request.getDistrict(), request.getCity(), request.getCountry())) {
            return repository.findByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(
                    request.getApartmentNumber(), request.getBuilding(), request.getStreet(),
                    request.getDistrict(), request.getCity(), request.getCountry());
        }

        Address address = mapper.forResponse().map(request, Address.class);
        address.setUsers(new HashSet<>());
        return  repository.save(address);
    }

    @Override
    public void deleteOwnerForAddress(User user, UUID addressId) {
        Address address = repository.findById(addressId).orElseThrow();
        Set<User> owners = address.getUsers();
        owners.remove(user);
        address.setUsers(owners);
        repository.save(address);
    }

}