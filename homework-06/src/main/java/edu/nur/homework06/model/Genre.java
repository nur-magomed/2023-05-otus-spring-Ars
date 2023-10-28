package edu.nur.homework06.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Genre {

    private long id;

    private String title;

    private Date createdDate;

    private Date modifiedDate;

    public Genre(String title, Date createdDate, Date modifiedDate) {
        this.title = title;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
