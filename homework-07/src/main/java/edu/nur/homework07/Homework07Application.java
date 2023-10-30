package edu.nur.homework07;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework07Application {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Homework07Application.class, args);
        Console.main(args);
    }
}
