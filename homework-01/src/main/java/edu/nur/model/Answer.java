package edu.nur.model;

import lombok.Data;

@Data
public class Answer {

    private long id;

    private String title;

    private boolean isCorrect;

    public Answer(long id, String title , boolean isCorrect) {
        this.id = id;
        this.title = title;
        this.isCorrect = isCorrect;
    }
}
