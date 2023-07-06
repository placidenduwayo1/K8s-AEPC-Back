package fr.acssi.cleanarchi_ms_company.domain.entity;

public enum ConnectionState {
    CONNECTED("connected"),
    DISCONNECTED("disconnected"),
    NOT_YET("not-yet");
    private String companyConnectState;

    ConnectionState(String companyState) {
        this.companyConnectState = companyState;
    }

    public String getCompanyConnectState() {
        return companyConnectState;
    }
}
