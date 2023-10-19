package edu.nur.homework03.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "quiz-app")
public class AppProps {

    private final String fileCsvPath;

    private final int minPassScore;

    @ConstructorBinding
    public AppProps(String fileCsvPath, int minPassScore) {
        this.fileCsvPath = fileCsvPath;
        this.minPassScore = minPassScore;
    }

}
