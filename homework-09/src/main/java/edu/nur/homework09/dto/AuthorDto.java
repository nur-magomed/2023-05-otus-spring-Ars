package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

    private long id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthDate;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Author toModelObject() {
        return new Author(id, firstName, lastName, birthDate, createdDate, modifiedDate);
    }

    public static AuthorDto fromModelObject(Author a) {
        return new AuthorDto(a.getId(), a.getFirstName(), a.getLastName(),
                a.getBirthDate(), a.getCreatedDate(), a.getModifiedDate());
    }

}
