package fr.acssi.cleanarchi_ms_address.infra.output.service;

import fr.acssi.cleanarchi_ms_address.domain.ports.output.AddressOutputService;
import fr.acssi.cleanarchi_ms_address.infra.input.feignclient.entity.EmployeeModel;
import fr.acssi.cleanarchi_ms_address.infra.input.feignclient.service.EmployeeServiceProxy;
import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressDto;
import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressModel;
import fr.acssi.cleanarchi_ms_address.domain.entity.Address;
import fr.acssi.cleanarchi_ms_address.domain.exceptions.AddressNotFoundException;
import fr.acssi.cleanarchi_ms_address.infra.output.mapper.AddressMapper;
import fr.acssi.cleanarchi_ms_address.infra.output.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressOutputServiceImpl implements AddressOutputService {
    private final AddressRepository addressRepository;
    private final   EmployeeServiceProxy employeeServiceProxy;
    @Override
    public List<Address> getAllAddresses() {
        List<AddressModel> addressModels = addressRepository.findByOrderByAddressIDAsc();

        return addressModels.stream()
                .map(AddressMapper::mapModelToClass)
                .toList();
    }

    @Override
    public Address createAddress(Address address) {

        AddressModel addressModel = AddressMapper.mapClassToModel(address);
        AddressModel savedAddress = addressRepository.save(addressModel);

        return AddressMapper.mapModelToClass(savedAddress);
    }

    @Override
    public List<Address> getAddressByInfo(AddressDto addressDto) {
        List<AddressModel> addressModels = addressRepository.findByNumAndStreetAndPbAndCityAndCountry(
                addressDto.getNum(), addressDto.getStreet(), addressDto.getPb(), addressDto.getCity(), addressDto.getCountry());
        return addressModels.stream()
                .map(AddressMapper::mapModelToClass)
                .toList();
    }

    @Override
    public void deleteAddress(String addressID) {
        addressRepository.deleteById(addressID);
    }

    @Override
    public Optional <Address> getAddressByID(String addressID) throws AddressNotFoundException {
       AddressModel addressModel = addressRepository.findById(addressID).orElseThrow(
               AddressNotFoundException::new
       );

       return Optional.of(AddressMapper.mapModelToClass(addressModel));
    }

    @Override
    public void updateAddress(Address address) {
        AddressModel addressModel = AddressMapper.mapClassToModel(address);
        AddressModel savedAddress = addressRepository.save(addressModel);
        AddressMapper.mapModelToClass(savedAddress);
    }

    @Override
    public List<EmployeeModel> getEmployeesLivingAtAddress(String addressID) {
        return employeeServiceProxy.getEmployeesLivingAtGivenAddress(addressID);
    }
}
