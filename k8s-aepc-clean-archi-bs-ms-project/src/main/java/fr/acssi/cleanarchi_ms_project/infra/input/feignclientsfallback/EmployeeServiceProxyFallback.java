package fr.acssi.cleanarchi_ms_project.infra.input.feignclientsfallback;

import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.EmployeeServiceProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmployeeServiceProxyFallback implements EmployeeServiceProxy {
    @Override
    public EmployeeModel getEmployeeByIdError(String employeeID) {
        EmployeeModel employeeApiError = new EmployeeModel();
        employeeApiError.setEmployeeID(ExceptionWarnMsg.EMPLOYEE_API_ERROR.getException());
        employeeApiError.setFirstname(ExceptionWarnMsg.EMPLOYEE_API_ERROR.getException());
        employeeApiError.setLastname(ExceptionWarnMsg.EMPLOYEE_API_ERROR.getException());
        employeeApiError.setEmail(ExceptionWarnMsg.EMPLOYEE_API_ERROR.getException());
        employeeApiError.setHireDate(LocalDateTime.now());
        employeeApiError.setEmployeeType(null);
        employeeApiError.setEmployeeState(null);
        return employeeApiError;
    }
}
