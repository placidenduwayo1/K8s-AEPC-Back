package fr.acssi.cleanarchi_ms_company.infra.output.models;

public class CompanyDto {
    private String companyName;
    private String agency;
    private String companyType;

    public CompanyDto() {
    }

    public CompanyDto(String companyName, String companyType, String agency) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.agency = agency;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
