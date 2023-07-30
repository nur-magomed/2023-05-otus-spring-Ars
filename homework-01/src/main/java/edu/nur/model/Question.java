package edu.nur.model;

import lombok.Data;

import java.util.Set;

@Data
public class Question {

    private long id;

    private String title;

    private Set<Answer> answers;


    public Question(long id, String title, Set<Answer> answers) {
        this.id = id;
        this.title = title;
        this.answers = answers;
    }

}
