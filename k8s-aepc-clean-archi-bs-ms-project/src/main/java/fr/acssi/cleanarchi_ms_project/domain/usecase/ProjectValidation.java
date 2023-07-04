package fr.acssi.cleanarchi_ms_project.domain.usecase;

import fr.acssi.cleanarchi_ms_project.domain.entity.Priority;
import fr.acssi.cleanarchi_ms_project.domain.entity.ProjectState;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeState;

public class ProjectValidation {
    private ProjectValidation(){}
    public static void projectFormatter(ProjectDto projectDto){
        projectDto.setProjectName(projectDto.getProjectName().strip().toUpperCase());
        projectDto.setDescription(projectDto.getDescription().strip());
        projectDto.setProjectState(projectDto.getProjectState().strip());
        projectDto.setEmployeeID(projectDto.getEmployeeID().strip());
        projectDto.setCompanyID(projectDto.getCompanyID().strip());

    }

    public static boolean areInvalidProjectFields(ProjectDto projectDto){
        return projectDto.getProjectName().isBlank()
                && projectDto.getPriority()<(byte) 1
                && projectDto.getPriority()>(byte) 6
                && projectDto.getProjectState().isBlank()
                && projectDto.getEmployeeID().isBlank()
                && projectDto.getCompanyID().isBlank()
                ;
    }

    public static boolean isValidPriority(byte priority){
        return priority == Priority.P1.getPriority()
                || priority == Priority.P2.getPriority()
                || priority == Priority.P3.getPriority()
                || priority == Priority.P4.getPriority()
                || priority == Priority.P5.getPriority()
                ;
    }

    public static boolean isValidProjectState(String projectState){
        return projectState.equals(ProjectState.END.getProjectState())
                || projectState.equals(ProjectState.ARCHIVE.getProjectState())
                || projectState.equals(ProjectState.OUTDATED.getProjectState())
                || projectState.equals(ProjectState.ONGOING.getProjectState())
                ;
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
