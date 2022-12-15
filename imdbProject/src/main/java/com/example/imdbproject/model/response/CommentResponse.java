package com.example.imdbproject.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {

    private String username;
    private String text;
    private Set<CommentResponse> replies;
}
