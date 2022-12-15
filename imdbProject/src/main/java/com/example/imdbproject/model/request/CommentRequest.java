package com.example.imdbproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {

    private String commentText;
    private String filmName;

}
