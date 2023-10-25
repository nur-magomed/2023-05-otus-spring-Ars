package edu.nur.homework09;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework09Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Homework09Application.class, args);
		System.out.printf("Чтобы перейти на страницу сайта открывай: %n%s%n",
				"http://localhost:8080");
		Console.main(args);
	}

}
