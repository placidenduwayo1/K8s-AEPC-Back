package fr.acssi.cleanarchi_ms_address.domain.usecase;

import fr.acssi.cleanarchi_ms_address.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_address.domain.ports.input.AddressInputService;
import fr.acssi.cleanarchi_ms_address.domain.ports.output.AddressOutputService;
import fr.acssi.cleanarchi_ms_address.infra.input.feignclient.entity.EmployeeModel;
import fr.acssi.cleanarchi_ms_address.infra.output.mapper.AddressMapper;
import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressDto;
import fr.acssi.cleanarchi_ms_address.domain.entity.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class AddressInputServiceImpl implements AddressInputService {

    private final AddressOutputService addressOutputService;
    public AddressInputServiceImpl(AddressOutputService addressOutputService) {
        this.addressOutputService = addressOutputService;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressOutputService.getAllAddresses();
    }

    @Override
    public Address createAddress(AddressDto addressDto) throws AddressFieldsEmptyException, AddressAlreadyExistsException,
            AddressNumInvalidException, AddressPBInvalidException {

        AddressValidation.addressFormatter(addressDto);

        if (!AddressValidation.areValidAddressTextFields(addressDto)) {
            throw new AddressFieldsEmptyException();
        } else if (!AddressValidation.isValidNum(addressDto.getNum())) {
            throw new AddressNumInvalidException();
        } else if (!AddressValidation.isValidPb(addressDto.getPb())) {
            throw new AddressPBInvalidException();
        }
        if (!getAddressByInfo(addressDto).isEmpty()) {
            throw new AddressAlreadyExistsException();
        }

        Address address = AddressMapper.mapDtoToClass(addressDto);
        address.setAddressID(UUID.randomUUID().toString());

        return addressOutputService.createAddress(address);
    }

    @Override
    public List<Address> getAddressByInfo(AddressDto addressDto) {
        return addressOutputService.getAddressByInfo(addressDto);
    }

    @Override
    public void deleteAddress(String addressID) throws AddressNotFoundException, AddressAssignedEmployeesException {
        Optional<Address> address = getAddressByID(addressID);
        List<EmployeeModel> employees = getEmployeesLivingAtAddressThis(address.get().getAddressID());
        if(!employees.isEmpty()){
            throw new AddressAssignedEmployeesException();
        }
        addressOutputService.deleteAddress(addressID);
    }

    @Override
    public Optional<Address> getAddressByID(String addressID) throws AddressNotFoundException {
        return Optional.of(addressOutputService.getAddressByID(addressID).orElseThrow(
                AddressNotFoundException::new
        ));
    }


    @Override
    public Address updateAddress(String addressID, AddressDto addressDto) throws AddressNotFoundException,
            AddressFieldsEmptyException, AddressPBInvalidException, AddressNumInvalidException, AddressAlreadyExistsException {
        AddressValidation.addressFormatter(addressDto);
        if(!AddressValidation.isValidPb(addressDto.getPb())){
            throw new AddressPBInvalidException();
        }
        else if(!AddressValidation.isValidNum(addressDto.getNum())){
            throw new AddressNumInvalidException();
        } else if (!AddressValidation.areValidAddressTextFields(addressDto)) {
            throw new AddressFieldsEmptyException();
        }
        if(!getAddressByInfo(addressDto).isEmpty()){
            throw new AddressAlreadyExistsException();
        }
        Address address = AddressMapper.mapDtoToClass(addressDto);
        Optional<Address> createdAddress = getAddressByID(addressID);
        createdAddress.ifPresentOrElse(value -> {
                    address.setAddressID(value.getAddressID());
                   addressOutputService.updateAddress(address);
                },
                AddressNotFoundException::new);

        return address;
    }

    @Override
    public List<EmployeeModel> getEmployeesLivingAtAddressThis(String addressID) throws AddressNotFoundException {
        Optional<Address> address = getAddressByID(addressID);
        return addressOutputService.getEmployeesLivingAtAddress(address.get().getAddressID());
    }
}
