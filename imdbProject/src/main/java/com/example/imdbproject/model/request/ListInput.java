package com.example.imdbproject.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListInput {

    private String filmId;
    private String listName;


}
