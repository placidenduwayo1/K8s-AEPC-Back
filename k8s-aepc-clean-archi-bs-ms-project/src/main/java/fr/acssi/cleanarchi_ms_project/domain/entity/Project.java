package fr.acssi.cleanarchi_ms_project.domain.entity;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;

import java.time.LocalDateTime;

public class Project {
    private String projectID;
    private String projectName;
    private String description;
    private byte priority;
    private String projectState;
    private LocalDateTime createdDate;
    private String employeeID;
    private EmployeeModel employee;
    private String companyID;
    private CompanyModel company;

    public Project() {
    }

    public Project(String projectName, String description, byte priority,
                   String projectState, String employeeID, EmployeeModel employeeModel,
                   String companyID, CompanyModel companyModel) {
        this.projectName = projectName;
        this.description = description;
        this.priority = priority;
        this.projectState = projectState;
        this.employeeID = employeeID;
        this.employee = employeeModel;
        this.companyID = companyID;
        this.company = companyModel;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }
}
