package fr.acssi.cleanarchi_ms_employee.domain.usecase;

import fr.acssi.cleanarchi_ms_employee.domain.exception_metrier.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.AddressModel;
import fr.acssi.cleanarchi_ms_employee.infra.output.model.EmployeeDto;

@SuppressWarnings("ALL")
public class EmployeeValidation {
    private EmployeeValidation(){}
    public static boolean areValidEmployeeRequiredFields(EmployeeDto employeeDto) {
        return !employeeDto.getFirstname().isBlank()
                && !employeeDto.getLastname().isBlank()
                && !employeeDto.getAddressID().isBlank()
                ;
    }

    public static void employeeFormatter(EmployeeDto employeeDto){
        employeeDto.setFirstname(employeeDto.getFirstname().strip().toUpperCase());
        employeeDto.setLastname(employeeDto.getLastname().strip().toUpperCase());
    }
    public static String buildEmployeeProfessionalEmail(String firstname, String lastname, String domain){
        lastname = lastname.strip();
        firstname = firstname.strip();
         lastname = lastname.replaceAll("\\s","-").toLowerCase();
        firstname = firstname.replaceAll("\\s","-").toLowerCase();

        return firstname+"."+lastname+"@"+domain+".fr";
    }

    public static boolean isInvalidAddressAPI(AddressModel addressModel){
        return addressModel
                .getAddressID()
                .equals(ExceptionWarnMsg.ADDRESS_API_ERROR.getException());
    }
}
