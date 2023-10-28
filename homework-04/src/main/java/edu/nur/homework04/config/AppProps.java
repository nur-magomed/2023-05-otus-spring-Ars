package edu.nur.homework04.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@Getter
@ConfigurationProperties(prefix = "quiz-app")
public class AppProps {

    private final int minPassScore;

    private final Locale locale;

    @ConstructorBinding
    public AppProps(int minPassScore, Locale locale) {
        this.minPassScore = minPassScore;
        this.locale = locale;
    }

}
