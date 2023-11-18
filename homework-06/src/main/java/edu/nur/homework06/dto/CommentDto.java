package edu.nur.homework06.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {

    private long id;

    private BookDto bookDto;

    private String message;

}
