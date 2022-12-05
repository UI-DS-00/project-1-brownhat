package com.example.imdbproject.controller;

import com.example.imdbproject.model.request.Input;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.service.FilterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/filter")
public class FilterController {
    private FilterServiceImpl filterService ;



    @GetMapping("/year")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByYear(@RequestBody  Input input) {
        return new ResponseEntity<>(filterService.filterByYear(Integer.parseInt(input.getInput())), HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByGenre(){
        return new ResponseEntity<>(filterService.filterByGenre("1"), HttpStatus.OK);
    }

    @GetMapping("/actor")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByActors(){
        return new ResponseEntity<>(filterService.filterByActor("amir"), HttpStatus.OK);
    }

    @GetMapping("/region")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByRegion(){
        return new ResponseEntity<>(filterService.filterByRegion("1"), HttpStatus.OK);
    }

}