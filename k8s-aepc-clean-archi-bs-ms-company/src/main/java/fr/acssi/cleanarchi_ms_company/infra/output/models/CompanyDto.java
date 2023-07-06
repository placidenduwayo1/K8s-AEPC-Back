package fr.acssi.cleanarchi_ms_company.infra.output.models;

public class CompanyDto {
    private String companyName;
    private String agency;
    private String companyType;
    private String companyConnectState;

    public CompanyDto() {
    }

    public CompanyDto(String companyName,
                      String agency,
                      String companyType,
                      String companyConnectState) {
        this.companyName = companyName;
        this.agency = agency;
        this.companyType = companyType;
        this.companyConnectState = companyConnectState;
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

    public String getCompanyConnectState() {
        return companyConnectState;
    }

    public void setCompanyConnectState(String companyConnectState) {
        this.companyConnectState = companyConnectState;
    }
}
