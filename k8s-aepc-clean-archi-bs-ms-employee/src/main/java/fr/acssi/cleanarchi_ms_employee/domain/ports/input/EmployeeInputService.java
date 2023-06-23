package fr.acssi.cleanarchi_ms_employee.domain.ports.input;

import fr.acssi.cleanarchi_ms_employee.domain.entity.Employee;
import fr.acssi.cleanarchi_ms_employee.domain.exception_metrier.*;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.ProjectModel;
import fr.acssi.cleanarchi_ms_employee.infra.output.model.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeInputService {
    List<Employee> getAllEmployees();
    Employee createEmployee(EmployeeDto employeeDto) throws EmployeeFieldsInvalidException,
            EmployeeAlreadyExistsException, EmployeeCreationErrorDueToAddressAPIException;
    Optional<AddressModel> getAddressByID(String addressID);

    List<Employee> getEmployeeByInfo(EmployeeDto employeeDto);
    Optional<Employee> getEmployeeByID(String employeeID) throws EmployeeNotFoundException;
    Employee updateEmployee(String employeeID, EmployeeDto employeeDto) throws EmployeeNotFoundException,
            EmployeeFieldsInvalidException, EmployeeCreationErrorDueToAddressAPIException, EmployeeAlreadyExistsException;
    void deleteEmployee(String employeeID) throws EmployeeNotFoundException, EmployeeAssociatedProjectsException;
    List<Employee> getEmployeesLivingAtGivenAddress(String addressID);
    List<ProjectModel> getProjectsAssignedToEmployee(String employeeID) throws EmployeeNotFoundException;
}
