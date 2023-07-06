package fr.acssi.cleanarchi_ms_employee.domain.usecase;

import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeState;
import fr.acssi.cleanarchi_ms_employee.domain.entity.EmployeeType;
import fr.acssi.cleanarchi_ms_employee.domain.exceptions.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.output.model.EmployeeDto;

public class EmployeeValidation {
    private EmployeeValidation(){}
    public static boolean areInvalidEmployeeRequiredFields(EmployeeDto employeeDto) {
        return employeeDto.getFirstname().isBlank()
                && employeeDto.getLastname().isBlank()
                && employeeDto.getEmployeeType().isBlank()
                && employeeDto.getEmployeeState().isBlank()
                && employeeDto.getAddressID().isBlank();
    }

    public static boolean isValidEmployeeState(String employeeState){
        return employeeState.equals(EmployeeState.ACTIVE.getEmployeeState())
                || employeeState.equals(EmployeeState.HISTORIZED.getEmployeeState());
    }

    public static boolean isValidEmployeeType(String employeeType){
        return employeeType.equals(EmployeeType.CEO.getEmployeeType())
                || employeeType.equals(EmployeeType.CTO.getEmployeeType())
                || employeeType.equals(EmployeeType.CTO.getEmployeeType())
                || employeeType.equals(EmployeeType.HR.getEmployeeType())
                || employeeType.equals(EmployeeType.TECH_M.getEmployeeType())
                || employeeType.equals(EmployeeType.TAM.getEmployeeType())
                || employeeType.equals(EmployeeType.COM_M.getEmployeeType())
                || employeeType.equals(EmployeeType.EMPL.getEmployeeType())
                || employeeType.equals(EmployeeType.SE.getEmployeeType());
    }

    public static void employeeFormatter(EmployeeDto employeeDto){
        employeeDto.setFirstname(employeeDto.getFirstname().strip().toUpperCase());
        employeeDto.setLastname(employeeDto.getLastname().strip().toUpperCase());
        employeeDto.setEmployeeState(employeeDto.getEmployeeState().strip());
        employeeDto.setEmployeeType(employeeDto.getEmployeeType().strip());
        employeeDto.setAddressID(employeeDto.getAddressID().strip());
    }
    public static String buildEmployeeProfessionalEmail(
            String firstname, String lastname, String domain){
        lastname = lastname.strip();
        firstname = firstname.strip();
         lastname = lastname.replaceAll("\\s","-").toLowerCase();
        firstname = firstname.replaceAll("\\s","-").toLowerCase();

        return firstname+"."+lastname+"@"+domain+".fr";
    }

    public static boolean isInvalidRemoteAddressAPI(AddressModel addressModel){
        return addressModel
                .getAddressID()
                .equals(ExceptionWarnMsg
                        .REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION.getException());
    }
}
