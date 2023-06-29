package fr.acssi.cleanarchi_ms_project.domain.ports.input;

import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.*;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.entity.Project;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;

import java.util.List;
import java.util.Optional;

public interface ProjectInputService {
    List<Project> getAllProjects();
    List<Project> getProjectByInfo(ProjectDto projectDto);
    Optional<Project> getProjectByID(String projectID) throws ProjectNotFoundException;
    Optional<EmployeeModel> getEmployeeByID(String employeeID);
    Optional<CompanyModel> getCompanyByID(String companyID);
    Project createProject(ProjectDto projectDto) throws ProjectAlreadyExistsException, ProjectFieldsEmptyException,
            RemoteEmployeeApiUnavailableException, RemoteCompanyApiUnavailableException,
            RemoteEmployeeStateNotAcceptableException;
    void deleteProject(String projectID) throws ProjectNotFoundException, ProjectIsAssignedToCompanyException, ProjectIsAssignedToEmployeeException;
    Project updateProject(String projectID, ProjectDto projectDto) throws ProjectNotFoundException,
            RemoteEmployeeApiUnavailableException, RemoteCompanyApiUnavailableException, ProjectFieldsEmptyException,
            RemoteEmployeeStateNotAcceptableException, ProjectAlreadyExistsException;

    List<Project> getProjectsAssignedToCompany(String companyID);
    List<Project> getProjectsAssignedToEmployee(String employeeID);
}
