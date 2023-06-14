package edu.nur.domain;

import lombok.Data;

@Data
public class Answer {

    private long id;
    private String title;

    public Answer() {
    }

    public Answer(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
