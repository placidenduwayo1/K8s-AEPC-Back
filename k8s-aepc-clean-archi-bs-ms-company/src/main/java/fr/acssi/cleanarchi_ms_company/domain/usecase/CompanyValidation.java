package fr.acssi.cleanarchi_ms_company.domain.usecase;

import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;

public class CompanyValidation {
    private CompanyValidation(){
        //private constructor to hide default public constructor
    }

    public static boolean areInvalidCompanyTextFields(CompanyDto companyDto) {
        return companyDto.getCompanyName().isBlank()
                && companyDto.getAgency().isBlank();
    }

    public static void companyFormatter(CompanyDto companyDto) {
        companyDto.setCompanyName(companyDto.getCompanyName().strip().toUpperCase());
        companyDto.setAgency(companyDto.getAgency().strip().toUpperCase());
    }
}
