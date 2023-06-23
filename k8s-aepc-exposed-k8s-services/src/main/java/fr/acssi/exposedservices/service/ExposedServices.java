package fr.acssi.exposedservices.service;

import java.util.List;

public interface ExposedServices {
    List<String> getAllK8sExposedServices();
}
