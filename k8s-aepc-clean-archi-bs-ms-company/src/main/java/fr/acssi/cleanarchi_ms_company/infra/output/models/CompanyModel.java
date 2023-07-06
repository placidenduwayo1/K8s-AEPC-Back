package fr.acssi.cleanarchi_ms_company.infra.output.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@AllArgsConstructor @NoArgsConstructor @Data
public class CompanyModel {
    @Id
    @GenericGenerator(name = "uuid")
    private String companyID;
    private String companyName;
    private String agency;
    private String companyType;
    private LocalDateTime connectedDate;
    private String companyConnectState;
}
