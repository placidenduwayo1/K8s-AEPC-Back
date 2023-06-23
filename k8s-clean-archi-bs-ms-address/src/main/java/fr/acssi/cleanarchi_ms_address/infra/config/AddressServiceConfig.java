package fr.acssi.cleanarchi_ms_address.infra.config;

import fr.acssi.cleanarchi_ms_address.domain.ports.output.AddressOutputService;
import fr.acssi.cleanarchi_ms_address.domain.usecase.AddressInputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceConfig {

    @Bean
    public AddressInputServiceImpl configureAddressUseCase(@Autowired AddressOutputService addressOutputService){
        return new AddressInputServiceImpl(addressOutputService);
    }
}
