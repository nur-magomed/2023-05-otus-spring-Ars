package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

    private long id;

    private String firstName;

    private String lastName;

    public Author toModelObject() {
        return new Author(id, firstName, lastName);
    }

    public static AuthorDto fromModelObject(Author a) {
        return new AuthorDto(a.getId(), a.getFirstName(), a.getLastName());
    }

}
