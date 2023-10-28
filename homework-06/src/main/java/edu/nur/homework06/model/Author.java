package edu.nur.homework06.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Author {

    private long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private Date createdDate;

    private Date modifiedDate;

    public Author(String firstName, String lastName, Date birthDate, Date createdDate, Date modifiedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
