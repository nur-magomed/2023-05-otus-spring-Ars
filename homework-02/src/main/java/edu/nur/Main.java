package edu.nur;

import edu.nur.io.QuestionPrinter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionPrinter printer = context.getBean(QuestionPrinter.class);
        printer.printQuestions();
    }
}