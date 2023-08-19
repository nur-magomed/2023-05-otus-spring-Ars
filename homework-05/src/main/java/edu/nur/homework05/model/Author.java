package edu.nur.homework05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(birthDate, author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate);
    }
}
