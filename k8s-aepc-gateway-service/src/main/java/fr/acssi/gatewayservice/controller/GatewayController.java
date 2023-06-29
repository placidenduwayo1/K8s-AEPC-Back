package fr.acssi.gatewayservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Value("${spring.message}")
    private String welcome;
    @GetMapping(value = "/")
    public String getWelcome(){
        return welcome;
    }
}
