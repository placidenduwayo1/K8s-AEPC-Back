package fr.acssi.cleanarchi_ms_address.infra.input.controller;

import fr.acssi.cleanarchi_ms_address.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_address.domain.ports.input.AddressInputService;
import fr.acssi.cleanarchi_ms_address.infra.input.feignclient.entity.EmployeeModel;
import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressDto;
import fr.acssi.cleanarchi_ms_address.domain.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class
AddressController {
    @Value("${spring.message}")
    private String message;
    @GetMapping(value = "/")
    public String getMessage(){
        return message;
    }
    private final AddressInputService addressInputService;
    @GetMapping(value = "/addresses", produces = "application/json")
    public List<Address> getAllAddresses(){
        return addressInputService
                .getAllAddresses();
    }

    @PostMapping(value = "/addresses")
    public Address createAddress(@RequestBody AddressDto addressDto)
            throws AddressFieldsEmptyException,
            AddressAlreadyExistsException {
        return addressInputService
                .createAddress(addressDto);
    }

    @DeleteMapping(value = "/addresses/{addressID}")
    public void deleteAddress(@PathVariable(name = "addressID") String addressID) throws
            AddressNotFoundException,
            AddressAssignedEmployeesException {
        addressInputService
                .deleteAddress(addressID);
    }

    @PutMapping(value = "/addresses/{addressID}")
    public Address updateAddress(
            @PathVariable(name = "addressID") String addressID,
            @RequestBody AddressDto addressDto)
            throws AddressFieldsEmptyException,
            AddressNotFoundException,
            AddressAlreadyExistsException {
        return addressInputService
                .updateAddress(addressID, addressDto);
    }

    @GetMapping(value = "/addresses/{addressID}", produces = "application/json")
    public Optional<Address> getAddress(
            @PathVariable(name = "addressID") String addressID) throws
            AddressNotFoundException {
        return addressInputService
                .getAddressByID(addressID);
    }
    @GetMapping(value = "/employees/addresses/{addressID}", produces = "application/json")
    public   List<EmployeeModel> employeesAtAddressThis(
            @PathVariable(name = "addressID") String addressID) throws
            AddressNotFoundException {
        return addressInputService
                .getEmployeesLivingAtAddressThis(addressID);
    }
}
