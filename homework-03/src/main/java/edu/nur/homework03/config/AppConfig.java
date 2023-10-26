package edu.nur.homework03.config;

import edu.nur.homework03.io.InputOutputService;
import edu.nur.homework03.io.InputOutputServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class AppConfig {

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.out, System.in);
    }

}