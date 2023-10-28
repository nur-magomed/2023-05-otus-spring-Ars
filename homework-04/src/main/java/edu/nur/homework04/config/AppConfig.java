package edu.nur.homework04.config;

import edu.nur.homework04.io.InputOutputService;
import edu.nur.homework04.io.InputOutputServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.out, System.in);
    }

}