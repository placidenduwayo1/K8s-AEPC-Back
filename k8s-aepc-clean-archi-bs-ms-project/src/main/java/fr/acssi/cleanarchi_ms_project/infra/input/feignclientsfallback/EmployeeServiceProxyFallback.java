package fr.acssi.cleanarchi_ms_project.infra.input.feignclientsfallback;

import fr.acssi.cleanarchi_ms_project.domain.exceptions.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.EmployeeServiceProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmployeeServiceProxyFallback implements EmployeeServiceProxy {
    @Override
    public EmployeeModel getEmployeeById(String employeeID) {
        EmployeeModel employeeApiError = new EmployeeModel();
        employeeApiError.setEmployeeID(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        employeeApiError.setFirstname(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        employeeApiError.setLastname(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        employeeApiError.setEmail(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        employeeApiError.setHireDate(LocalDateTime.now());
        employeeApiError.setEmployeeType(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        employeeApiError.setEmployeeState(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_API_EXCEPTION
                .getException());
        return employeeApiError;
    }
}
