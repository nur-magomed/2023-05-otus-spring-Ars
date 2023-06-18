package edu.nur.model;

import lombok.Data;

import java.util.Set;

@Data
public class Question {

    private long id;

    private String title;

    private Set<Answer> answers;

    private long correctAnswerId;

    public Question(long id, String title, Set<Answer> answers, long correctAnswerId) {
        this.id = id;
        this.title = title;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

}
