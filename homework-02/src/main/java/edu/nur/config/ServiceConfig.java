package edu.nur.config;

import edu.nur.dao.QuestionDao;
import edu.nur.io.InputOutputService;
import edu.nur.io.InputOutputServiceImpl;
import edu.nur.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public QuestionService questionService(QuestionDao questionDao) {
        return new QuestionServiceImpl(questionDao);
    }

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.out, System.in);
    }

    @Bean
    public QuizResultsService quizResultsService() {
        return new QuizResultsServiceImpl();
    }

    @Bean
    public QuizRunner quizRunner(QuestionService questionService,
                                 InputOutputService inputOutputService,
                                 QuizResultsService quizResultsService) {
        return new QuizRunner(questionService, inputOutputService, quizResultsService);
    }
}
