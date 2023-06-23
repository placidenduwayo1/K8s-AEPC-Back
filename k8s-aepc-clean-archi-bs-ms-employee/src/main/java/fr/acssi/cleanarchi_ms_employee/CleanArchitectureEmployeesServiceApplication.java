package fr.acssi.cleanarchi_ms_employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchitectureEmployeesServiceApplication {

	public static void main(String[] args) {
		new SpringApplication(CleanArchitectureEmployeesServiceApplication.class)
				.run(args);
	}

}
