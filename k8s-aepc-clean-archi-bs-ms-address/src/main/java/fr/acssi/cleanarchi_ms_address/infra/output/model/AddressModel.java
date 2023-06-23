package fr.acssi.cleanarchi_ms_address.infra.output.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "addresses")
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class AddressModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressID;
    private int num;
    private String street;
    private int pb;
    private String city;
    private String country;
}
