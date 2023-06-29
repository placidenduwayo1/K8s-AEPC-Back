package fr.acssi.cleanarchi_ms_employee.infra.input.feignclientfallback;

import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.services.AddressServiceProxy;
import org.springframework.stereotype.Component;

import static fr.acssi.cleanarchi_ms_employee.domain.exception_metrier.ExceptionWarnMsg.REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION;

@Component
public class AddressServiceProxyFallback implements AddressServiceProxy {
    @Override
    public AddressModel getAddressById(String addressID) {
        AddressModel addressApiError = new AddressModel();
        addressApiError.setAddressID(REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        addressApiError.setStreet(REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        addressApiError.setCity(REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        addressApiError.setCountry(REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
        return addressApiError;
    }
}
