package fr.acssi.cleanarchi_ms_employee.infra.input.feignclientfallback;

import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.services.AddressServiceProxy;
import org.springframework.stereotype.Component;

import java.util.List;

import static fr.acssi.cleanarchi_ms_employee.domain.exceptions.ExceptionWarnMsg.REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION;

@Component
public class AddressServiceProxyFallback implements AddressServiceProxy {
    @Override
    public AddressModel getAddressById(String addressID) {
        AddressModel remoteAddressUnavailable = new AddressModel();
        remoteAddressUnavailable.setAddressID(
                REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        remoteAddressUnavailable.setStreet(
                REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        remoteAddressUnavailable.setCity(
                REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        remoteAddressUnavailable.setCountry(
                REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        return remoteAddressUnavailable;
    }

    @Override
    public List<AddressModel> getAllAddresses() {
        AddressModel remoteAddressUnavailable = new AddressModel();
        remoteAddressUnavailable.setAddressID(
                REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException()
        );
        return List.of(remoteAddressUnavailable);
    }
}
