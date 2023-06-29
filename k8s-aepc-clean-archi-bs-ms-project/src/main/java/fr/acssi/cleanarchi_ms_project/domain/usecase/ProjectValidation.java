package fr.acssi.cleanarchi_ms_project.domain.usecase;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeState;

public class ProjectValidation {
    private ProjectValidation(){}
    public static void projectFormatter(ProjectDto projectDto){
        projectDto.setProjectName(projectDto.getProjectName().strip().toUpperCase());
        projectDto.setEmployeeID(projectDto.getEmployeeID().strip());

    }

    public static boolean areInvalidProjectFields(ProjectDto projectDto){
        return projectDto.getProjectName().isBlank()
                && projectDto.getEmployeeID().isBlank();
    }

    public static boolean isInvalidRemoteEmployeeAPI(EmployeeModel employeeModel){
        return employeeModel.getEmployeeID().equals(ExceptionWarnMsg.REMOTE_EMPLOYEE_API_EXCEPTION.getException());
    }

    public static boolean isInvalidRemoteCompanyAPI(CompanyModel companyModel){
        return  companyModel.getCompanyID().equals(ExceptionWarnMsg.REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION.getException());
    }

    public static boolean isInvalidRemoteEmployeeState(EmployeeModel employeeModel){
        return employeeModel.getEmployeeState().equals(EmployeeState.HISTORIZED);
    }
}
