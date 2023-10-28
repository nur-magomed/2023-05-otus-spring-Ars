package edu.nur.homework04.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Answer {

    private long id;

    private String title;

    private boolean isCorrect;
    
}
