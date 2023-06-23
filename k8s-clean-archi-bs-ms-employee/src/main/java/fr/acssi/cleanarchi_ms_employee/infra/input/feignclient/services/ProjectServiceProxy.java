package fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.services;

import fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models.ProjectModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "k8s-aepc-bs-ms-project-svc")
public interface ProjectServiceProxy {
    @GetMapping(value = "/projects/employees/{employeeID}", produces = "application/json")
    List<ProjectModel> getProjectsAssignedToEmployee(@PathVariable(name = "employeeID") String employeeID);
}
