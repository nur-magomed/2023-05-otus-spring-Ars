package edu.nur.homework03.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@Getter
@ConfigurationProperties(prefix = "quiz-app")
public class AppProps {

    private final String fileCsvPath;

    private final int minPassScore;

    private final Locale locale;

    @ConstructorBinding
    public AppProps(String fileCsvPath, int minPassScore, Locale locale) {
        this.fileCsvPath = fileCsvPath;
        this.minPassScore = minPassScore;
        this.locale = locale;
    }

}
