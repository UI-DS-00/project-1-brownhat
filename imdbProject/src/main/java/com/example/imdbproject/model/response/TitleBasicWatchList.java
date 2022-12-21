package com.example.imdbproject.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class TitleBasicWatchList {

    String name;
    Set<String> movieName = new HashSet<>();
    String ownerUsername;
}