package fr.acssi.cleanarchi_ms_company;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CleanArchitectureCompanyServiceApplication {
	public static void main(String[] args) {
		SpringApplication app =new SpringApplication(
				CleanArchitectureCompanyServiceApplication.class);
				app.run(args);
	}

}
