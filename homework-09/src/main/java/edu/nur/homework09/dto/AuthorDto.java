package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

    private long id;

    @NotBlank(message = "{firstName-field-should-not-be-blank}")
    @Size(min = 2, max = 30, message = "{firstName-field-should-has-expected-size}")
    private String firstName;

    @NotBlank(message = "{lastName-field-should-not-be-blank}")
    @Size(min = 2, max = 30, message = "{lastName-field-should-has-expected-size}")
    private String lastName;

    public Author toModelObject() {
        return new Author(id, firstName, lastName);
    }

    public static AuthorDto fromModelObject(Author a) {
        return new AuthorDto(a.getId(), a.getFirstName(), a.getLastName());
    }

}
