package com.example.imdbproject.controller;


import com.example.imdbproject.model.FavouriteList;
import com.example.imdbproject.model.request.Input;
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

    @GetMapping ("/others/favouriteList")
    public ResponseEntity<Set<FavouriteList>> getFavouriteLists(@RequestBody Input input) {
        //return new ResponseEntity<>(showDataService.favouriteList(input.getInput()),HttpStatus.OK);
        return new ResponseEntity<>(showDataService.othersFavouriteList("tt0012349"),HttpStatus.OK);
    }
}