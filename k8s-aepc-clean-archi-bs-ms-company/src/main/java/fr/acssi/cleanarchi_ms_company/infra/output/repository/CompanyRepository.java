package fr.acssi.cleanarchi_ms_company.infra.output.repository;

import fr.acssi.cleanarchi_ms_company.infra.output.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, String> {
    List<CompanyModel> findByOrderByCompanyIDAsc();
    List<CompanyModel> findByCompanyNameAndAgencyAndCompanyTypeAndCompanyConnectState(
            String companyName,
            String agency,
            String companyType,
            String companyConnectState);
}
