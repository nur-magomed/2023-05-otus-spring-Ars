package edu.nur.homework05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class Book {

    private long id;

    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    private Date createdDate;

    private Date modifiedDate;

}
