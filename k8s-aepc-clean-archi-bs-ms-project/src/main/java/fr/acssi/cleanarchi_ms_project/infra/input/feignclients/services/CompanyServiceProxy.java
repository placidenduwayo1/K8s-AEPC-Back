package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclientsfallback.CompanyServiceProxyFallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "k8s-aepc-bs-ms-company", fallback = CompanyServiceProxyFallback.class)
@Qualifier("company-service-proxy")
public interface CompanyServiceProxy {
    @GetMapping(value = "/companies/{companyID}", produces = "application/json")
    CompanyModel getCompanyById(@PathVariable(name = "companyID") String companyID);
}

