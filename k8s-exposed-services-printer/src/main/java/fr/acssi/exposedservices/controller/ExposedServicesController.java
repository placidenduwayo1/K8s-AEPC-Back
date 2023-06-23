package fr.acssi.exposedservices.controller;

import fr.acssi.exposedservices.service.ExposedServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExposedServicesController {
    private final ExposedServices exposedServices;
    public ExposedServicesController(ExposedServices exposedServices) {
        this.exposedServices = exposedServices;
    }
    @GetMapping(value = "/services")
   public List<String> getK8sExposedServices(){
        return exposedServices.getAllK8sExposedServices();
   }

}
