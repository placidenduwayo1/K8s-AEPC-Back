package fr.acssi.cleanarchi_ms_project.domain.usecase;

import fr.acssi.cleanarchi_ms_project.domain.entity.Project;
import fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers.*;
import fr.acssi.cleanarchi_ms_project.domain.ports.input.ProjectInputService;
import fr.acssi.cleanarchi_ms_project.domain.ports.output.ProjectOutputService;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.output.mapper.ProjectMapper;
import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectDto;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ProjectInputServiceImpl implements ProjectInputService {
    private final ProjectOutputService projectOutputService;

    public ProjectInputServiceImpl(ProjectOutputService projectOutputService) {
        this.projectOutputService = projectOutputService;
    }

    private void innerUtilityCheckProject(ProjectDto projectDto) throws ProjectFieldsEmptyException,
            ProjectPriorityUnrecognisedException, ProjectStateUnrecognisedException {
        if (ProjectValidation.areInvalidProjectFields(projectDto)) {
            throw new ProjectFieldsEmptyException();
        }
        else if(!ProjectValidation.isValidPriority(projectDto.getPriority())){
            throw new ProjectPriorityUnrecognisedException();
        }
        else if (! ProjectValidation.isValidProjectState(projectDto.getProjectState())){
            throw new ProjectStateUnrecognisedException();
        }
    }

    private void innerUtilityCheckEmployee(EmployeeModel employeeModel) throws
            RemoteEmployeeApiUnavailableException, RemoteEmployeeStateNotAcceptableException {
        if (ProjectValidation.isInvalidRemoteEmployeeAPI(employeeModel)) {
            throw new RemoteEmployeeApiUnavailableException(employeeModel.toString());
        } else if (ProjectValidation.isInvalidRemoteEmployeeState(employeeModel)) {
            throw new RemoteEmployeeStateNotAcceptableException();
        }
    }
    private void innerUtilityCheckCompany(CompanyModel companyModel) throws
            RemoteEmployeeApiUnavailableException, RemoteEmployeeStateNotAcceptableException {
        if (ProjectValidation.isInvalidRemoteCompanyAPI(companyModel)) {
            throw new RemoteEmployeeApiUnavailableException(companyModel.toString());
        }
    }
    @Override
    public List<Project> getAllProjects() {
        return projectOutputService.getAllProjects();
    }

    @Override
    public List<Project> getProjectByInfo(ProjectDto projectDto) {
        return projectOutputService.getProjectByInfo(projectDto);
    }

    @Override
    public Optional<Project> getProjectByID(String projectID) throws ProjectNotFoundException {
        Project project = projectOutputService.getProjectByID(projectID).orElseThrow(
                ProjectNotFoundException::new
        );

        return Optional.of(project);
    }

    @Override
    public Optional<EmployeeModel> getEmployeeByID(String employeeID) {
        return Optional.of(projectOutputService.getEmployeeByID(employeeID));
    }

    @Override
    public Optional<CompanyModel> getCompanyByID(String companyID) {
        return Optional.of(projectOutputService.getCompanyByID(companyID));
    }

    @Override
    public Project createProject(ProjectDto projectDto) throws ProjectAlreadyExistsException,
            ProjectFieldsEmptyException, RemoteEmployeeApiUnavailableException,
            RemoteCompanyApiUnavailableException, RemoteEmployeeStateNotAcceptableException {
        ProjectValidation.projectFormatter(projectDto);
        innerUtilityCheckProject(projectDto);
        List<Project> projects = getProjectByInfo(projectDto);
        if (!projects.isEmpty()) {
            throw new ProjectAlreadyExistsException();
        }

        Project project = ProjectMapper.dtoToClass(projectDto);

        Optional<EmployeeModel> employeeModel = getEmployeeByID(projectDto.getEmployeeID());
        innerUtilityCheckEmployee(employeeModel.get());
        Optional<CompanyModel> companyModel = getCompanyByID(projectDto.getCompanyID());
        innerUtilityCheckCompany(companyModel.get());

        if (ProjectValidation.isInvalidRemoteCompanyAPI(companyModel.get())) {
            throw new RemoteCompanyApiUnavailableException(companyModel.toString());
        }

        project.setProjectID(UUID.randomUUID().toString());
        project.setCreatedDate(LocalDateTime.now(ZoneId.of("Europe/Paris")));
        project.setEmployee(employeeModel.get());
        project.setCompany(companyModel.get());
        return projectOutputService.createProject(project);
    }

    @Override
    public void deleteProject(String projectID) throws ProjectNotFoundException, ProjectIsAssignedToCompanyException,
            ProjectIsAssignedToEmployeeException {
        Optional<Project> project = getProjectByID(projectID);

        if (project.isEmpty()) {
            throw new ProjectNotFoundException();
        } else if (!getProjectsAssignedToCompany(project.get().getCompanyID()).isEmpty()) {
            throw new ProjectIsAssignedToCompanyException();
        } else if (!getProjectsAssignedToEmployee(project.get().getEmployeeID()).isEmpty()) {
            throw new ProjectIsAssignedToEmployeeException();
        }
    }

    @Override
    public Project updateProject(String projectID, ProjectDto projectDto) throws ProjectNotFoundException,
            RemoteEmployeeApiUnavailableException, RemoteCompanyApiUnavailableException, ProjectFieldsEmptyException,
            RemoteEmployeeStateNotAcceptableException, ProjectAlreadyExistsException {

        ProjectValidation.projectFormatter(projectDto);
        innerUtilityCheckProject(projectDto);

        Project project = ProjectMapper.dtoToClass(projectDto);
        Optional<Project> createdProject = projectOutputService.getProjectByID(projectID);
        createdProject.ifPresentOrElse(
                value -> {
                    project.setProjectID(value.getProjectID());
                    project.setCompanyID(value.getCompanyID());
                    project.setCreatedDate(value.getCreatedDate());
                },
                ProjectNotFoundException::new
        );
        Optional<EmployeeModel> employeeModel = getEmployeeByID(projectDto.getEmployeeID());
        innerUtilityCheckEmployee(employeeModel.get());
        Optional<CompanyModel> companyModel = getCompanyByID(projectDto.getCompanyID());
        innerUtilityCheckCompany(companyModel.get());

        if (!getProjectByInfo(projectDto).isEmpty()) {
            throw new ProjectAlreadyExistsException();
        }
        project.setEmployee(employeeModel.get());
        project.setCompany(companyModel.get());

        return projectOutputService.updateProject(project);
    }

    @Override
    public List<Project> getProjectsAssignedToCompany(String companyID) {
        Optional<CompanyModel> company = getCompanyByID(companyID);
        return projectOutputService.getProjectsAssignedToCompany(company.get().getCompanyID());
    }

    @Override
    public List<Project> getProjectsAssignedToEmployee(String employeeID) {
        Optional<EmployeeModel> employee = getEmployeeByID(employeeID);
        return projectOutputService.getProjectsAssignedToEmployee(employee.get().getEmployeeID());
    }
}
