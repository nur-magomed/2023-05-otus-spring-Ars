package edu.nur.homework05;

import edu.nur.homework05.dao.AuthorDao;
import edu.nur.homework05.dao.BookDao;
import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Homework05Application {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Homework05Application.class, args);


        Author test = new Author( "Isa", "Isaev", new Date(), new Date(), new Date());
        Author test2 = new Author( "Isa2", "Isaev2", new Date(), new Date(), new Date());
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        authorDao.save(test);
        authorDao.save(test2);
        List<Author> authors = authorDao.getAll();
        BookDao bookDao = context.getBean(BookDao.class);
//        Book book = bookDao.getById(5);

        List<Book> books = bookDao.getAll();

        Console.main(args);
    }

}
