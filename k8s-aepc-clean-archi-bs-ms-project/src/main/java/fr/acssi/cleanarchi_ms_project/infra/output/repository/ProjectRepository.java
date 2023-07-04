package fr.acssi.cleanarchi_ms_project.infra.output.repository;

import fr.acssi.cleanarchi_ms_project.infra.output.models.ProjectModel;
import fr.acssi.cleanarchi_ms_project.domain.entity.ProjectState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectModel, String> {
    List<ProjectModel> findByOrderByCreatedDateDesc();
    List<ProjectModel> findByProjectNameAndDescriptionAndProjectStateAndEmployeeIDAndCompanyID(
            String projectName,
            String description,
            String projectState,
            String employeeID,
            String companyID);
    List<ProjectModel> findByCompanyID(String companyID);
    List<ProjectModel> findByEmployeeID(String employeeID);
}
