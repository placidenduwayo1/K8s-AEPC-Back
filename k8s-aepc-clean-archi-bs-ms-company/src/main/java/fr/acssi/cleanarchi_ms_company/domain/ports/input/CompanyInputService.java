package fr.acssi.cleanarchi_ms_company.domain.ports.input;

import fr.acssi.cleanarchi_ms_company.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_company.infra.input.feignclient.model.ProjectModel;
import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;
import fr.acssi.cleanarchi_ms_company.domain.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyInputService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyByID(String companyID) throws
            CompanyNotFoundException;
    List<Company> getCompanyByInfos(CompanyDto companyDto);
    Company createCompany(CompanyDto companyDto) throws
            CompanyAlreadyExistsException,
            CompanyFieldsEmptyException,
            CompanyTypeUnrecognizedException,
            CompanyConnectStateUnrecognizedException;
    Company updateCompany(String companyID, CompanyDto companyDto) throws
            CompanyNotFoundException,
            CompanyFieldsEmptyException,
            CompanyAlreadyExistsException,
            CompanyTypeUnrecognizedException;
    void deleteCompany(String companyID) throws
            CompanyNotFoundException,
            CompanyAssociedProjectsException;
    List<ProjectModel> getProjectsAssignedToCompany(String companyID) throws
            CompanyNotFoundException;
}
