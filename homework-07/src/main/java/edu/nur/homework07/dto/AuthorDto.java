package edu.nur.homework07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

    private long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

}