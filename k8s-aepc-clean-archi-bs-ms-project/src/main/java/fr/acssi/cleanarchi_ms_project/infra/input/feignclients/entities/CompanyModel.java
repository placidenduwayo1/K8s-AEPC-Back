package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities;

import java.time.LocalDateTime;

public class CompanyModel {
    private String companyID;
    private String companyName;
    private String agency;
    private String companyType;
    private LocalDateTime connectedDate;
    private String companyConnectState;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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

    public LocalDateTime getConnectedDate() {
        return connectedDate;
    }

    public void setConnectedDate(LocalDateTime connectedDate) {
        this.connectedDate = connectedDate;
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

    @Override
    public String toString() {
        return "Company:[" +
                "ID='" + companyID + '\'' +
                ", company name='" + companyName + '\'' +
                ", company type=" + companyType +
                ", connected date=" + connectedDate +
                ", agency='" + agency + '\'' +
                ", connection state= "+companyConnectState+ '\'' +
                ']';
    }
}
