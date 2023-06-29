package fr.acssi.cleanarchi_ms_project.domain.ports.output;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.entity.Project;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.RemoteCompanyApiUnavailableException;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.RemoteEmployeeApiUnavailableException;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.ProjectNotFoundException;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;

import java.util.List;
import java.util.Optional;

public interface ProjectOutputService {
    List<Project> getAllProjects();
    List<Project> getProjectByInfo(ProjectDto projectDto);
    Optional<Project> getProjectByID(String projectID) throws ProjectNotFoundException;
    EmployeeModel getEmployeeByID(String employeeID);
    CompanyModel getCompanyByID(String companyID);
    Project createProject(Project project) throws RemoteEmployeeApiUnavailableException, RemoteCompanyApiUnavailableException;
    void deleteProject(Project project);
    Project updateProject(Project project);
    List<Project> getProjectsAssignedToCompany(String companyID);
    List<Project> getProjectsAssignedToEmployee(String employeeID);
}
