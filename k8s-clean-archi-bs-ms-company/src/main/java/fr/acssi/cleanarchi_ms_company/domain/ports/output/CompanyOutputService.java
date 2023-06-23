package fr.acssi.cleanarchi_ms_company.domain.ports.output;

import fr.acssi.cleanarchi_ms_company.infra.input.feignclient.model.ProjectModel;
import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;
import fr.acssi.cleanarchi_ms_company.domain.entity.Company;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.CompanyNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CompanyOutputService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException;
    List<Company> getCompanyByInfos(CompanyDto companyDto);
    Company createCompany(Company company);
    Company updateCompany(Company company);
    void deleteCompany(Company company);
    List<ProjectModel> getProjectsAssignedToCompany(String companyID);
}
