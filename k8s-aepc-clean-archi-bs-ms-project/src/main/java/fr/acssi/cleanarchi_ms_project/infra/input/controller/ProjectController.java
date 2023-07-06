package fr.acssi.cleanarchi_ms_project.infra.input.controller;

import fr.acssi.cleanarchi_ms_project.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_project.domain.ports.input.ProjectInputService;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.entity.Project;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.CompanyServiceProxy;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.EmployeeServiceProxy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {
    @Value("${spring.message}")
    private String welcome;

    @GetMapping(value = "/")
    public String getWelcome() {
        return welcome;
    }

    private final ProjectInputService projectInputService;
    private final EmployeeServiceProxy employeeServiceProxy;
    private final CompanyServiceProxy companyServiceProxy;

    public ProjectController(
            ProjectInputService projectInputService,
            @Qualifier("employee-service-proxy") EmployeeServiceProxy employeeServiceProxy,
            @Qualifier("company-service-proxy") CompanyServiceProxy companyServiceProxy) {
        this.projectInputService = projectInputService;
        this.employeeServiceProxy = employeeServiceProxy;
        this.companyServiceProxy = companyServiceProxy;
    }

    private void innerUtilityMethod(Project project) {
        project.setCompany(companyServiceProxy
                        .getCompanyById(project.getCompanyID()));
        project.setEmployee(employeeServiceProxy
                .getEmployeeById(project.getEmployeeID()));
    }

    @GetMapping(value = "/projects", produces = "application/json")
    public List<Project> getAllProjects() {
        List<Project> projects = projectInputService
                .getAllProjects();
        projects.forEach(this::innerUtilityMethod);
        return projects;
    }

    @PostMapping(value = "/projects")
    public Project createProject(@RequestBody ProjectDto projectDto) throws
            ProjectAlreadyExistsException,
            ProjectFieldsEmptyException,
            RemoteEmployeeApiUnavailableException,
            RemoteCompanyApiUnavailableException,
            RemoteEmployeeStateNotAcceptableException,
            RemoteCompanyConnectionStateUnauthorized {
        Project createdProject = projectInputService
                .createProject(projectDto);
        innerUtilityMethod(createdProject);
        return createdProject;
    }

    @PutMapping(value = "/projects/{projectID}")
    public Project updateProject(
            @PathVariable(name = "projectID") String projectID,
            @RequestBody ProjectDto projectDto) throws
            ProjectNotFoundException,
            RemoteEmployeeApiUnavailableException,
            RemoteCompanyApiUnavailableException,
            ProjectFieldsEmptyException,
            RemoteEmployeeStateNotAcceptableException,
            ProjectAlreadyExistsException,
            RemoteCompanyConnectionStateUnauthorized {
        Project updatedProject = projectInputService
                .updateProject(projectID, projectDto);
        innerUtilityMethod(updatedProject);
        return updatedProject;
    }

    @DeleteMapping(value = "/projects/{projectID}")
    public void deleteProject(@PathVariable(name = "projectID") String projectID) throws
            ProjectNotFoundException,
            ProjectIsAssignedToEmployeeException,
            ProjectIsAssignedToCompanyException {
        projectInputService
                .deleteProject(projectID);
    }

    @GetMapping(value = "/employees/{employeeID}", produces = "application/json")
    public EmployeeModel getEmployee(@PathVariable(name = "employeeID") String employeeID) {
        return employeeServiceProxy
                .getEmployeeById(employeeID);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @GetMapping(value = "/projects/{projectID}", produces = "application/json")
    public Optional<Project> getProject(
            @PathVariable(name = "projectID") String projectID) throws
            ProjectNotFoundException {
        Optional<Project> project = projectInputService
                .getProjectByID(projectID);
        innerUtilityMethod(project.get());

        return project;
    }

    @GetMapping(value = "/projects/companies/{companyID}", produces = "application/json")
    public List<Project> getProjectAssignedToCompany(
            @PathVariable(name = "companyID") String companyID) {
        List<Project> projects = projectInputService
                .getProjectsAssignedToCompany(companyID);
        projects.forEach(this::innerUtilityMethod);

        return projects;
    }

    @GetMapping(value = "/projects/employees/{employeeID}", produces = "application/json")
    public List<Project> getProjectAssignedToEmployee(
            @PathVariable(name = "employeeID") String employeeID) {
        List<Project> projects = projectInputService
                .getProjectsAssignedToEmployee(employeeID);
        projects.forEach(this::innerUtilityMethod);
        return projects;
    }
}
