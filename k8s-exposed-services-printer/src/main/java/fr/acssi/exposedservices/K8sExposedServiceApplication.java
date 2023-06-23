package fr.acssi.exposedservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class K8sExposedServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(K8sExposedServiceApplication.class);
		app.run(args);
	}

}
