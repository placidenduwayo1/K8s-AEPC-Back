package fr.acssi.cleanarchi_ms_company.domain.entity;

public enum CompanyType {
    CLIENT("client"),
    PROSPECT("prospect"),
    ESN("esn");
    private final String type;
    CompanyType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
