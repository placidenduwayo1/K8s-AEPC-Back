package fr.acssi.cleanarchi_ms_company.domain.ports.input;

import fr.acssi.cleanarchi_ms_company.infra.input.feignclient.model.ProjectModel;
import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;
import fr.acssi.cleanarchi_ms_company.domain.entity.Company;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.CompanyAlreadyExistsException;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.CompanyAssociedProjectsException;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.CompanyFieldsEmptyException;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.CompanyNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CompanyInputService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException;
    List<Company> getCompanyByInfos(CompanyDto companyDto);
    Company createCompany(CompanyDto companyDto) throws CompanyAlreadyExistsException, CompanyFieldsEmptyException;
    Company updateCompany(String companyID, CompanyDto companyDto) throws CompanyNotFoundException,
            CompanyFieldsEmptyException, CompanyAlreadyExistsException;
    void deleteCompany(String companyID) throws CompanyNotFoundException, CompanyAssociedProjectsException;
    List<ProjectModel> getProjectsAssignedToCompany(String companyID) throws CompanyNotFoundException;
}
