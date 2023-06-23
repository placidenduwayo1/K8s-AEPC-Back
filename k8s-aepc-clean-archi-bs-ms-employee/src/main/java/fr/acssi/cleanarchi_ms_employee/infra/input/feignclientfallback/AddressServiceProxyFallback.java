package fr.acssi.cleanarchi_ms_employee.infra.input.feignclientfallback;

import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.services.AddressServiceProxy;
import org.springframework.stereotype.Component;

import static fr.acssi.cleanarchi_ms_employee.domain.exception_metrier.ExceptionWarnMsg.ADDRESS_API_ERROR;

@Component
public class AddressServiceProxyFallback implements AddressServiceProxy {
    @Override
    public AddressModel getAddressById(String addressID) {
        AddressModel addressApiError = new AddressModel();
        addressApiError.setAddressID(ADDRESS_API_ERROR.getException());
        addressApiError.setStreet(ADDRESS_API_ERROR.getException());
        addressApiError.setCity(ADDRESS_API_ERROR.getException());
        addressApiError.setCountry(ADDRESS_API_ERROR.getException());
        return addressApiError;
    }
}
