package fr.acssi.exposedservices.service;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ExposedServicesImpl implements ExposedServices{
    private final DiscoveryClient discoveryClient;

    public ExposedServicesImpl(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }
    @Override
    public List<String> getAllK8sExposedServices() {
        return discoveryClient.getServices();
    }
}
