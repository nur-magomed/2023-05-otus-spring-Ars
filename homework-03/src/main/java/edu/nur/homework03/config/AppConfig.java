package edu.nur.homework03.config;

import edu.nur.homework03.io.CsvReader;
import edu.nur.homework03.io.InputOutputService;
import edu.nur.homework03.io.InputOutputServiceImpl;
import edu.nur.homework03.io.Reader;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class AppConfig {

    private final AppProps appProps;

    public AppConfig(AppProps appProps) {
        this.appProps = appProps;
    }

    @Bean
    public Reader reader() {
        return new CsvReader(appProps.getFileCsvPath());
    }

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.out, System.in);
    }

}
