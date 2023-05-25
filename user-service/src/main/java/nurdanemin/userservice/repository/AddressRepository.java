package nurdanemin.userservice.repository;

import nurdanemin.userservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    boolean existsByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(int apartment, String building,
                                                                                    String Street,
                                                                                    String District,
                                                                                    String City,
                                                                                    String Country);
    Address findByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(int apartment, String building,
                                                                                  String Street,
                                                                                  String District,
                                                                                  String City,
                                                                                  String Country);


}
