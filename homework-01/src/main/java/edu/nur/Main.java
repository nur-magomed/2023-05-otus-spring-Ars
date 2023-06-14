package edu.nur;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import edu.nur.domain.Answer;
import edu.nur.domain.Question;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
//        System.out.println("HomeWork 01!");
//        System.out.println("Will load context");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
//        System.out.println("Loaded context");
//        Test test = (Test) context.getBean("test");
//        test.foo();




        ClassLoader cl = Main.class.getClassLoader();
        URL url = cl.getResource("quiz.csv");

        Map<Long, Question> questionMap = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(url.getFile()))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> {
                long qId = Long.parseLong(x[0]);
                String qTitle = x[1];
                long correctAnsId = Long.parseLong(x[2]);
                long aId = Long.parseLong(x[3]);
                String aTitle = x[4];
                Answer answer = new Answer(aId, aTitle);
                if (!questionMap.containsKey(qId)) {
                  Question question = new Question(qId, qTitle, new HashSet<>(), correctAnsId);
                  questionMap.put(qId, question);
                }
                questionMap.get(qId).getAnswers().add(answer);
            });
        } catch (Exception e){
            System.out.println(e.toString());
        }

        for (Question q: questionMap.values()) {
            System.out.println(q.getTitle());
            for (Answer a: q.getAnswers()) {
                System.out.println(a.getTitle());
            }
        }
    }
}