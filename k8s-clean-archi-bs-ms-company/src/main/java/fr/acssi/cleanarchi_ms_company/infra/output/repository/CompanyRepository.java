package fr.acssi.cleanarchi_ms_company.infra.output.repository;

import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyModel;
import fr.acssi.cleanarchi_ms_company.domain.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, String> {
    List<CompanyModel> findByOrderByCompanyIDAsc();
    List<CompanyModel> findByCompanyNameAndCompanyTypeAndAgency(String companyName, CompanyType companyType, String agency);
}
