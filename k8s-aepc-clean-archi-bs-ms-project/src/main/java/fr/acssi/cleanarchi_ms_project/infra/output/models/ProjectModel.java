package fr.acssi.cleanarchi_ms_project.infra.output.models;

import fr.acssi.cleanarchi_ms_project.domain.entity.Priority;
import fr.acssi.cleanarchi_ms_project.domain.entity.ProjectState;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor @Data
@Entity @Table(name = "projects")
public class ProjectModel {
    @Id
    @GenericGenerator(name = "uuid")
    private String projectID;
    private String projectName;
    private String description;
    private byte priority;
    private String projectState;
    private LocalDateTime createdDate;
    private String employeeID;
    @Transient
    private EmployeeModel employee;
    private String companyID;
    @Transient
    private CompanyModel company;
}
