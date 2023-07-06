package fr.acssi.cleanarchi_ms_company.domain.usecase;

import fr.acssi.cleanarchi_ms_company.domain.entity.CompanyType;
import fr.acssi.cleanarchi_ms_company.domain.entity.ConnectionState;
import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;

public class CompanyValidation {
    private CompanyValidation(){
        //private constructor to hide default public constructor
    }

    public static boolean areInvalidCompanyFields(CompanyDto companyDto) {
        return companyDto.getCompanyName().isBlank()
                && companyDto.getAgency().isBlank()
                && companyDto.getCompanyType().isBlank()
                && companyDto.getCompanyConnectState().isBlank();
    }

    public static boolean isValidCompanyType(String companyType){
        return companyType.equals(CompanyType.CLIENT.getType())
                || companyType.equals(CompanyType.PROSPECT.getType())
                || companyType.equals(CompanyType.ESN.getType());
    }
    public static boolean isValidConnectionState(String connectState){
        return connectState.equals(ConnectionState
                .CONNECTED.getCompanyConnectState())
                || connectState.equals(ConnectionState.NOT_YET
                .getCompanyConnectState())
                || connectState.equals(ConnectionState.DISCONNECTED
                .getCompanyConnectState());
    }

    public static void companyFormatter(CompanyDto companyDto) {
        companyDto.setCompanyName(
                companyDto.getCompanyName().strip().toUpperCase());
        companyDto.setAgency(
                companyDto.getAgency().strip().toUpperCase());
        companyDto.setCompanyType(
                companyDto.getCompanyType().strip().toLowerCase());
        companyDto.setCompanyConnectState(
                companyDto.getCompanyConnectState().strip().toLowerCase());
    }
}
