package edu.nur.homework06.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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