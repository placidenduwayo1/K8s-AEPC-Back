package fr.acssi.cleanarchi_ms_project.infra.output.service;

import fr.acssi.cleanarchi_ms_project.infra.output.mapper.ProjectMapper;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.domain.entity.Project;
import fr.acssi.cleanarchi_ms_project.domain.exceptions.ProjectNotFoundException;
import fr.acssi.cleanarchi_ms_project.domain.ports.output.ProjectOutputService;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.CompanyServiceProxy;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectModel;
import fr.acssi.cleanarchi_ms_project.infra.output.repository.ProjectRepository;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.EmployeeServiceProxy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectOutputServiceImpl implements ProjectOutputService {
    private final ProjectRepository projectRepository;
    private final EmployeeServiceProxy employeeServiceProxy;
    private final CompanyServiceProxy companyServiceProxy;

    public ProjectOutputServiceImpl(
            ProjectRepository projectRepository,
            @Qualifier("employee-service-proxy") EmployeeServiceProxy employeeServiceProxy,
            @Qualifier("company-service-proxy") CompanyServiceProxy companyServiceProxy
    ) {
        this.projectRepository = projectRepository;
        this.employeeServiceProxy = employeeServiceProxy;
        this.companyServiceProxy = companyServiceProxy;
    }

    private List<Project> innerUtilityMethod1(List<ProjectModel> projectModels) {
        return projectModels.stream()
                .map(ProjectMapper::mapToClass)
                .toList();
    }

    private Project innerUtilityMethod2(ProjectModel projectModel){
       return ProjectMapper.mapToClass(projectModel);
    }
    @Override
    public List<Project> getAllProjects() {
        List<ProjectModel> projectModels = projectRepository
                .findByOrderByCreatedDateDesc();
        return innerUtilityMethod1(projectModels);
    }

    @Override
    public List<Project> getProjectByInfo(ProjectDto projectDto) {
        List<ProjectModel> projectModels = projectRepository
                .findByProjectNameAndDescriptionAndProjectStateAndEmployeeIDAndCompanyID(
                        projectDto.getProjectName(),
                        projectDto.getDescription(),
                        projectDto.getProjectState(),
                        projectDto.getEmployeeID(),
                        projectDto.getCompanyID());
        return innerUtilityMethod1(projectModels);
    }

    @Override
    public Optional<Project> getProjectByID(String projectID) throws
            ProjectNotFoundException {
        ProjectModel projectModel = projectRepository
                .findById(projectID).orElseThrow(
                        ProjectNotFoundException::new
                );

        return Optional.of(innerUtilityMethod2(projectModel));
    }

    @Override
    public EmployeeModel getEmployeeByID(String employeeID) {
        return employeeServiceProxy
                .getEmployeeById(employeeID);
    }

    @Override
    public CompanyModel getCompanyByID(String companyID) {
        return companyServiceProxy
                .getCompanyById(companyID);
    }

    @Override
    public Project createProject(Project project) {
        ProjectModel projectModel = ProjectMapper.mapToModel(project);
        ProjectModel savedProject= projectRepository.save(projectModel);
        return innerUtilityMethod2(savedProject);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository
                .delete(ProjectMapper.mapToModel(project));
    }

    @Override
    public Project updateProject(Project project) {
        ProjectModel projectModel = ProjectMapper.mapToModel(project);
        ProjectModel savedProject = projectRepository
                .save(projectModel);
        return innerUtilityMethod2(savedProject);
    }

    @Override
    public List<Project> getProjectsAssignedToCompany(String companyID) {
        List<ProjectModel> projects = projectRepository
                .findByCompanyID(companyID);
        return innerUtilityMethod1(projects);
    }

    @Override
    public List<Project> getProjectsAssignedToEmployee(String employeeID) {
        List<ProjectModel> projects = projectRepository
                .findByEmployeeID(employeeID);
        return innerUtilityMethod1(projects);
    }
}
