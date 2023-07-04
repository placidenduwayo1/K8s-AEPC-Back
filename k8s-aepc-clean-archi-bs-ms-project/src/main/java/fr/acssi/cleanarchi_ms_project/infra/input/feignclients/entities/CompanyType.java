package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities;

public enum CompanyType {
    CLIENT("client"),
    PROSPECT("prospect"),
    ESN("esn");
    private final String companyType;
    CompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyType() {
        return companyType;
    }
}
