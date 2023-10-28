package edu.nur.homework03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Answer {

    private long id;

    private String title;

    private boolean isCorrect;
    
}
