package fr.acssi.cleanarchi_ms_address.domain.ports.input;

import fr.acssi.cleanarchi_ms_address.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_address.infra.input.feignclient.entity.EmployeeModel;
import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressDto;
import fr.acssi.cleanarchi_ms_address.domain.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressInputService {
    List<Address> getAllAddresses();
    Address createAddress(AddressDto addressDto) throws AddressFieldsEmptyException, AddressAlreadyExistsException,
            AddressNumInvalidException, AddressPBInvalidException;
    List<Address> getAddressByInfo(AddressDto addressDto);
    void deleteAddress (String addressID) throws AddressNotFoundException, AddressAssignedEmployeesException;
    Optional<Address> getAddressByID(String addressID) throws  AddressNotFoundException;
    Address updateAddress(String addressID, AddressDto addressDto) throws AddressNotFoundException, AddressFieldsEmptyException,
            AddressPBInvalidException, AddressNumInvalidException, AddressAlreadyExistsException;
    List<EmployeeModel> getEmployeesLivingAtAddressThis(String addressID) throws AddressNotFoundException;
}
