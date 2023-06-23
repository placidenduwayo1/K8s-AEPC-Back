package fr.acssi.cleanarchi_ms_address.infra.output.repository;

import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, String> {
    List<AddressModel> findByOrderByAddressIDAsc();
    List<AddressModel> findByNumAndStreetAndPbAndCityAndCountry(int num, String street, int pb, String city, String country);
}
