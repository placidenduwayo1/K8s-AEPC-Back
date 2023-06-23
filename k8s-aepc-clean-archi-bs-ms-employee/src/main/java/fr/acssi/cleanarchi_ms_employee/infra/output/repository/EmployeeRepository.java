package fr.acssi.cleanarchi_ms_employee.infra.output.repository;

import fr.acssi.cleanarchi_ms_employee.infra.output.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, String> {
    List<EmployeeModel> findByFirstnameAndLastnameAndAddressID(String firstname, String lastname, String addressID);
    List<EmployeeModel> findByOrderByHireDateDesc();
    List<EmployeeModel> findByAddressID(String addressID);
}
