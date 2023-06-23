package fr.acssi.cleanarchi_ms_employee.infra.output.model;

import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeState;
import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class EmployeeDto {
    private String firstname;
    private String lastname;
    private EmployeeState employeeState;
    private EmployeeType employeeType;
    private String addressID;
}
