package edu.nur.homework03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class Question {

    private long id;

    private String title;

    private Set<Answer> answers;

}
