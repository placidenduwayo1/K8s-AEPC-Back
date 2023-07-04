package fr.acssi.cleanarchi_ms_employee.infra.output.model;

import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeState;
import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@AllArgsConstructor @NoArgsConstructor @Data
public class EmployeeModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String employeeID;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private LocalDateTime hireDate;
    private String employeeState;
    private String employeeType;
    private String addressID;
    @Transient
    private AddressModel address;
}
