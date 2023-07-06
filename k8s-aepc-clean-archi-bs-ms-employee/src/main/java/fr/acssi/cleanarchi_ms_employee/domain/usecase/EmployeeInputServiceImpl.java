package fr.acssi.cleanarchi_ms_employee.domain.usecase;

import fr.acssi.cleanarchi_ms_employee.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_employee.domain.ports.input.EmployeeInputService;
import fr.acssi.cleanarchi_ms_employee.domain.ports.output.EmployeeOutputService;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.ProjectModel;
import fr.acssi.cleanarchi_ms_employee.infra.output.mapper.EmployeeMapper;
import fr.acssi.cleanarchi_ms_employee.infra.output.model.EmployeeDto;
import fr.acssi.cleanarchi_ms_employee.domain.entity.Employee;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class EmployeeInputServiceImpl implements EmployeeInputService {
    private final EmployeeOutputService employeeOutputService;

    public EmployeeInputServiceImpl(EmployeeOutputService employeeOutputService) {
        this.employeeOutputService = employeeOutputService;
    }

    private void innerUtilityEmployeeCheck(EmployeeDto employeeDto) throws
            EmployeeFieldsInvalidException,
            EmployeeAlreadyExistsException,
            EmployeeStateUnrecognizedException,
            EmployeeTypeUnrecognizedException {
        if (EmployeeValidation.areInvalidEmployeeRequiredFields(employeeDto)) {
            throw new EmployeeFieldsInvalidException();
        } else if (!EmployeeValidation.isValidEmployeeState(
                employeeDto.getEmployeeState())) {
            throw new EmployeeStateUnrecognizedException();
        } else if (!EmployeeValidation.isValidEmployeeType(
                employeeDto.getEmployeeType())) {
            throw new EmployeeTypeUnrecognizedException();
        }
        if (!getEmployeeByInfo(employeeDto).isEmpty()) {
            throw new EmployeeAlreadyExistsException();
        }
    }

    private void innerUtilityCheckRemoteAddress(AddressModel address) throws
            RemoteAddressApiUnavailableException {
        if (EmployeeValidation.isInvalidRemoteAddressAPI(address)) {
            throw new RemoteAddressApiUnavailableException(address.toString());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeOutputService
                .getAllEmployees();
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws
            EmployeeFieldsInvalidException,
            EmployeeAlreadyExistsException,
            RemoteAddressApiUnavailableException,
            EmployeeStateUnrecognizedException,
            EmployeeTypeUnrecognizedException {

        EmployeeValidation.employeeFormatter(employeeDto);
        innerUtilityEmployeeCheck(employeeDto);
        Optional<AddressModel> address = getAddressByID(
                employeeDto.getAddressID());
        innerUtilityCheckRemoteAddress(address.get());
        Employee employee = EmployeeMapper.mapDtoToClass(employeeDto);

        employee.setEmployeeID(UUID.randomUUID().toString());
        employee.setEmail(
                EmployeeValidation.buildEmployeeProfessionalEmail(
                        employeeDto.getFirstname(),
                        employeeDto.getLastname(),
                        "acssi"));
        employee.setHireDate(LocalDateTime.now(ZoneId.of("Europe/Paris")));
        employee.setAddressID(address.get().getAddressID());
        employee.setAddress(address.get());
        return employeeOutputService
                .createEmployee(employee);
    }

    @Override
    public Optional<AddressModel> getAddressByID(String addressID) {
        AddressModel address = employeeOutputService
                .getAddressByID(addressID);
        return Optional.of(address);
    }

    @Override
    public List<AddressModel> getAllAddresses() {
        return employeeOutputService
                .getAllAddresses();
    }

    @Override
    public List<Employee> getEmployeeByInfo(EmployeeDto employeeDto) {
        return employeeOutputService
                .getEmployeeByInfo(employeeDto);
    }

    @Override
    public Optional<Employee> getEmployeeByID(String employeeID) throws
            EmployeeNotFoundException {
        return Optional.of(employeeOutputService
                .getEmployeeByID(employeeID)
                .orElseThrow(EmployeeNotFoundException::new));
    }

    @Override
    public Employee updateEmployee(
            String employeeID,
            EmployeeDto employeeDto) throws
            EmployeeNotFoundException,
            EmployeeFieldsInvalidException,
            RemoteAddressApiUnavailableException,
            EmployeeAlreadyExistsException,
            EmployeeStateUnrecognizedException,
            EmployeeTypeUnrecognizedException {

        EmployeeValidation.employeeFormatter(employeeDto);
        innerUtilityEmployeeCheck(employeeDto);
        Employee employee = EmployeeMapper.mapDtoToClass(employeeDto);
        Optional<Employee> createdEmployee = getEmployeeByID(employeeID);
        createdEmployee.ifPresentOrElse(value -> {
                    employee.setEmployeeID(value.getEmployeeID());
                    employee.setEmail(
                            EmployeeValidation.buildEmployeeProfessionalEmail(
                                    employee.getFirstname(),
                                    employee.getLastname(),
                                    "acssi")
                    );
                    employee.setHireDate(value.getHireDate());
                },
                EmployeeNotFoundException::new
        );

        Optional<AddressModel> addressModel = getAddressByID(
                employeeDto.getAddressID());
        innerUtilityCheckRemoteAddress(addressModel.get());
        employee.setAddress(addressModel.get());
        return employeeOutputService
                .updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String employeeID) throws
            EmployeeNotFoundException,
            EmployeeAssociatedProjectsException {

        Optional<Employee> employee = getEmployeeByID(employeeID);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        } else if (!getProjectsAssignedToEmployee(
                employee.get().getEmployeeID()).isEmpty()) {
            throw new EmployeeAssociatedProjectsException();
        }

        employeeOutputService
                .deleteEmployee(employee.get());
    }

    @Override
    public List<Employee> getEmployeesLivingAtGivenAddress(String addressID) {
        Optional<AddressModel> address = getAddressByID(addressID);
        return employeeOutputService
                .getEmployeesLivingAtGivenAddress(address.get().getAddressID());
    }

    @Override
    public List<ProjectModel> getProjectsAssignedToEmployee(String employeeID) throws
            EmployeeNotFoundException {
        Optional<Employee> employee = getEmployeeByID(employeeID);
        return employeeOutputService
                .getProjectsAssignedToEmployee(employee.get().getEmployeeID());
    }
}
