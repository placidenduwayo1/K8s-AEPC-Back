package fr.acssi.cleanarchi_ms_project.domain.usecase;

import fr.acssi.cleanarchi_ms_project.domain.entity.Priority;
import fr.acssi.cleanarchi_ms_project.domain.entity.ProjectState;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.exceptions.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;

public class ProjectValidation {
    private ProjectValidation(){}
    public static void projectFormatter(ProjectDto projectDto){
        projectDto.setProjectName(
                projectDto.getProjectName().strip().toUpperCase());
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
                && projectDto.getCompanyID().isBlank();
    }

    public static boolean isValidPriority(byte priority){
        return priority == Priority.P1.getProjectPriority()
                || priority == Priority.P2.getProjectPriority()
                || priority == Priority.P3.getProjectPriority()
                || priority == Priority.P4.getProjectPriority()
                || priority == Priority.P5.getProjectPriority();
    }

    public static boolean isValidProjectState(String projectState){
        return projectState.equals(ProjectState.END.getState())
                || projectState.equals(ProjectState.ARCHIVE.getState())
                || projectState.equals(ProjectState.OUTDATED.getState())
                || projectState.equals(ProjectState.ONGOING.getState());
    }

    public static boolean isInvalidRemoteEmployeeAPI(EmployeeModel employeeModel){
        return employeeModel.getEmployeeID().equals(
                ExceptionWarnMsg
                        .REMOTE_EMPLOYEE_API_EXCEPTION
                        .getException());
    }
    public static boolean isInvalidRemoteEmployeeState(EmployeeModel employeeModel){
        return employeeModel
                .getEmployeeState()
                .equals("historized");
    }
    public static boolean isInvalidRemoteCompanyAPI(CompanyModel companyModel){
        return  companyModel.getCompanyID().equals(
                ExceptionWarnMsg
                        .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                        .getException());
    }
    public static boolean isInvalidRemoteCompanyConnectionState(String connectState){
        return connectState.equals("not-yet")
                || connectState.equals("disconnected");
    }
}
