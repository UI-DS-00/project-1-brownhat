package com.example.imdbproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyRequest {
    private String commentText;
    private Long mainId;
}
