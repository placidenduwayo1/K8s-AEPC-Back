package fr.acssi.cleanarchi_ms_project.infra.input.feignclientsfallback;

import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities.CompanyModel;
import fr.acssi.cleanarchi_ms_project.domain.exceptions.ExceptionWarnMsg;
import fr.acssi.cleanarchi_ms_project.infra.input.feignclients.services.CompanyServiceProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompanyServiceProxyFallback implements CompanyServiceProxy {
    @Override
    public CompanyModel getCompanyById(String companyID) {
        CompanyModel companyApiError = new CompanyModel();
        companyApiError.setCompanyID(ExceptionWarnMsg
                .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                .getException());
        companyApiError.setCompanyName(ExceptionWarnMsg
                .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                .getException());
        companyApiError.setCompanyType(ExceptionWarnMsg
                .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                .getException());
        companyApiError.setAgency(ExceptionWarnMsg
                .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                .getException());
        companyApiError.setCompanyConnectState(ExceptionWarnMsg
                .REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION
                .getException());
        companyApiError.setConnectedDate(LocalDateTime.now());
        return companyApiError;
    }
}
