package com.example.imdbproject.controller;

import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.service.FilterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class FilterController {
    private FilterServiceImpl filterService ;



    @GetMapping("/yearFilter")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByYear(){
        return new ResponseEntity<>(filterService.filterByYear(1), HttpStatus.OK);
    }

    @GetMapping("/genreFilter")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByGenre(){
        return new ResponseEntity<>(filterService.filterByGenre("1"), HttpStatus.OK);
    }

    @GetMapping("/actorFilter")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByActors(){
        return new ResponseEntity<>(filterService.filterByActor("amir"), HttpStatus.OK);
    }

    @GetMapping("/regionFilter")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByRegion(){
        return new ResponseEntity<>(filterService.filterByRegion("1"), HttpStatus.OK);
    }

}