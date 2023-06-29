package fr.acssi.cleanarchi_ms_address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CleanArchitectureAddressServiceApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(
				CleanArchitectureAddressServiceApplication.class);
		app.run(args);
	}

}
