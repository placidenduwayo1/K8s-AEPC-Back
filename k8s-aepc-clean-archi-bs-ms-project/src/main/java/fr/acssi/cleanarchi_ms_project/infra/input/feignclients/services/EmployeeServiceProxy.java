package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.EmployeeModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclientsfallback.EmployeeServiceProxyFallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "k8s-aepc-bs-ms-employee", fallback = EmployeeServiceProxyFallback.class)
@Qualifier("employee-service-proxy")
public interface EmployeeServiceProxy {
    @GetMapping(value = "/employees/{employeeID}", produces = "application/json")
    EmployeeModel getEmployeeById(@PathVariable(name = "employeeID") String employeeID);
}
