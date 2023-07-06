package fr.acssi.cleanarchi_ms_address.domain.usecase;

import fr.acssi.cleanarchi_ms_address.infra.output.model.AddressDto;

public class AddressValidation {
    private AddressValidation() {
    }

    public static boolean areValidAddressFields(AddressDto addressDto) {
        return addressDto.getNum() > 0
                && !addressDto.getStreet().isBlank()
                && addressDto.getPb()>10000
                && !addressDto.getCity().isBlank()
                && !addressDto.getCountry().isBlank()
                ;
    }

    public static void addressFormatter(AddressDto addressDto) {
        addressDto.setStreet(addressDto.getStreet().strip().toUpperCase());
        addressDto.setCity(addressDto.getCity().strip().toUpperCase());
        addressDto.setCountry(addressDto.getCountry().strip().toUpperCase());
    }
}
