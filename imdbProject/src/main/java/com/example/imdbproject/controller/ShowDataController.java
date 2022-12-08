package com.example.imdbproject.controller;


import com.example.imdbproject.model.FavouriteList;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.service.ShowDataServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/data/show")
public class ShowDataController {

    private final ShowDataServiceImpl showDataService;

    //private final ReadingFilesImpl readingFiles;

    //all films service function

    @GetMapping("/allFilms")
    public ResponseEntity<Set<TitleBasicResponse>> getFilms(){
        return new ResponseEntity<>(showDataService.allMoviesData(), HttpStatus.OK);
    }


    //actors and directors service function

    @GetMapping ("/actors")
    public ResponseEntity<Set<NameBasicSummery>> getActors() {
        return new ResponseEntity<>(showDataService.ActorsAndDirectors("actor"),HttpStatus.OK);
    }


    @GetMapping ("/actresses")
    public ResponseEntity<Set<NameBasicSummery>> getActresses() {
        return new ResponseEntity<>(showDataService.ActorsAndDirectors("actress"),HttpStatus.OK);

    }


    @GetMapping ("/directors")
    public ResponseEntity<Set<NameBasicSummery>> getDirectors() {
        return new ResponseEntity<>(showDataService.ActorsAndDirectors("director"),HttpStatus.OK);
    }


    //======================================================no debug
    @GetMapping ("/others/favouriteList/{movieName}")
    public ResponseEntity<Set<FavouriteList>> getFavouriteLists() {
        return new ResponseEntity<>(showDataService.favouriteList(new TitleBasic("1")),HttpStatus.OK);
    }
}