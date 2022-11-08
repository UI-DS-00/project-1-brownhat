package com.example.imdbproject.controller;


import com.example.imdbproject.model.NameBasic;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.service.ShowDataService;
import com.example.imdbproject.service.ShowDataServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
public class ShowDataController {

    private final ShowDataServiceImpl showDataService;


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
        return new ResponseEntity<>(showDataService.ActorsAndDirectors("directors"),HttpStatus.OK);
    }
    //----------------------------------------------------------------------------------------------------

    @GetMapping("/allfilms/date")
    public ResponseEntity <Set<TitleBasicResponse>> getFilmsByDate() {
        return new ResponseEntity<>(showDataService.filmEndYear(), HttpStatus.OK);
    }

    @GetMapping("/allfilms/rate")
    public ResponseEntity <Set<TitleBasicResponse>> getFilmsByRate() {
        return new ResponseEntity<>(showDataService.filmRating(), HttpStatus.OK);
    }

}