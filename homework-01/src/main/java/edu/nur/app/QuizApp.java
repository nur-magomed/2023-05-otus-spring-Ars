package edu.nur.app;

import edu.nur.io.QuestionPrinter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuizApp {

    public static void run() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionPrinter printer = context.getBean(QuestionPrinter.class);
        printer.printQuestions();

    }

}
