package fr.acssi.cleanarchi_ms_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchitectureProjectServiceApplication {

	public static void main(String[] args) {
		new SpringApplication(CleanArchitectureProjectServiceApplication.class)
				.run(args);
	}

}
