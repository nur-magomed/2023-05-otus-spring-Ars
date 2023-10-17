package edu.nur.homework05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Data
public class Book {

    private long id;

    private String title;

    private Set<Author> authors;

    private Genre genre;

    private Date createdDate;

    private Date modifiedDate;

}
