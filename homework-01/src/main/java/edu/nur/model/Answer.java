package edu.nur.model;

import lombok.Data;

@Data
public class Answer {

    private long id;

    private String title;

    public Answer(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
