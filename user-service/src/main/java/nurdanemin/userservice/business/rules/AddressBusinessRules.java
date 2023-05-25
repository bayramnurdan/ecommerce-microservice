package nurdanemin.userservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.userservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressBusinessRules {
    private final AddressRepository repository;
    public boolean checkIfAddressExists(int apartment, String building, String street, String district, String city, String country){
        if (repository.existsByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(apartment, building,
                street, district, city, country)){
            return true;
        }
        return false;

    }


    public void checkIfExistsById(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException(Messages.Address.NotExists);
        }
    }


}

