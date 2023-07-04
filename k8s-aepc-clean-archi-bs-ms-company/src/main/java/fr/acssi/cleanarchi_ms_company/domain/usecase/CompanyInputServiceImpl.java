package fr.acssi.cleanarchi_ms_company.domain.usecase;

import fr.acssi.cleanarchi_ms_company.domain.entity.Company;
import fr.acssi.cleanarchi_ms_company.domain.exceptions.*;
import fr.acssi.cleanarchi_ms_company.domain.ports.input.CompanyInputService;
import fr.acssi.cleanarchi_ms_company.domain.ports.output.CompanyOutputService;
import fr.acssi.cleanarchi_ms_company.infra.input.feignclient.model.ProjectModel;
import fr.acssi.cleanarchi_ms_company.infra.output.mapper.CompanyMapper;
import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("ALL")
public class CompanyInputServiceImpl implements CompanyInputService {
    private final CompanyOutputService companyOutputService;

    public CompanyInputServiceImpl(CompanyOutputService companyOutputService) {
        this.companyOutputService = companyOutputService;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyOutputService.getAllCompanies();
    }

    @Override
    public Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException {

        return Optional.of(companyOutputService.getCompanyByID(companyID).orElseThrow(
                CompanyNotFoundException::new
        ));
    }

    @Override
    public List<Company> getCompanyByInfos(CompanyDto companyDto) {
        return companyOutputService.getCompanyByInfos(companyDto);
    }

    @Override
    public Company createCompany(CompanyDto companyDto) throws CompanyAlreadyExistsException,
            CompanyFieldsEmptyException, CompanyTypeUnrecognizedException {
       CompanyValidation.companyFormatter(companyDto);
        if (CompanyValidation.areInvalidCompanyTextFields(companyDto)) {
            throw new CompanyFieldsEmptyException();
        }
        if (!getCompanyByInfos(companyDto).isEmpty()) {
            throw new CompanyAlreadyExistsException();
        }
        if(!CompanyValidation.isValidCompanyType(companyDto.getCompanyType())){
            throw new CompanyTypeUnrecognizedException();
        }
        Company company = CompanyMapper.dtoToClass(companyDto);
        company.setCompanyID(UUID.randomUUID().toString());
        company.setConnectedDate(LocalDateTime.now(ZoneId.of("Europe/Paris")));
        return companyOutputService.createCompany(company);
    }

    @Override
    public Company updateCompany(String companyID, CompanyDto companyDto) throws CompanyNotFoundException,
            CompanyFieldsEmptyException, CompanyAlreadyExistsException, CompanyTypeUnrecognizedException {
        CompanyValidation.companyFormatter(companyDto);
        if(CompanyValidation.areInvalidCompanyTextFields(companyDto)){
            throw new CompanyFieldsEmptyException();
        }
        if(!getCompanyByInfos(companyDto).isEmpty()){
            throw new CompanyAlreadyExistsException();
        }
        if(!CompanyValidation.isValidCompanyType(companyDto.getCompanyType())){
            throw new CompanyTypeUnrecognizedException();
        }
        Company company = CompanyMapper.dtoToClass(companyDto);
        Optional<Company> createdCompany = getCompanyByID(companyID);
        createdCompany.ifPresentOrElse(value -> {
                    company.setCompanyID(value.getCompanyID());
                    company.setConnectedDate(value.getConnectedDate());

                },
                CompanyNotFoundException::new
        );

        return companyOutputService.updateCompany(company);
    }

    @Override
    public void deleteCompany(String companyID) throws CompanyNotFoundException, CompanyAssociedProjectsException {
        Optional<Company> company = getCompanyByID(companyID);
        if(company.isEmpty()){
           throw new CompanyNotFoundException();
        } else if (!getProjectsAssignedToCompany(company.get().getCompanyID()).isEmpty()) {
            throw new CompanyAssociedProjectsException();
        }

        companyOutputService.deleteCompany(company.get());
    }

    @Override
    public List<ProjectModel> getProjectsAssignedToCompany(String companyID) throws CompanyNotFoundException {
        Optional<Company> company = getCompanyByID(companyID);
        return companyOutputService.getProjectsAssignedToCompany(company.get().getCompanyID());
    }
}
