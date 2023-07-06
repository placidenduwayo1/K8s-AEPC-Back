package fr.acssi.cleanarchi_ms_company.domain.entity;

import java.time.LocalDateTime;

public class Company {
    private String companyID;
    private String companyName;
    private String agency;
    private String companyType;
    private LocalDateTime connectedDate;
    private String companyConnectState;

    public Company() {
    }

    public Company(String companyID,
                   String companyName,
                   String companyType,
                   LocalDateTime connectedDate,
                   String agency,
                   String companyConnectState) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyType = companyType;
        this.connectedDate = connectedDate;
        this.agency = agency;
        this.companyConnectState = companyConnectState;
    }

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
}
