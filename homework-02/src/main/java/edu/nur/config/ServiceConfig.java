package edu.nur.config;

import edu.nur.io.InputOutputService;
import edu.nur.io.InputOutputServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.out, System.in);
    }

}
