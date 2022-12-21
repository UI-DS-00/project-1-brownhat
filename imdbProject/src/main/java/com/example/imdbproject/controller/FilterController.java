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
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByGenre(@RequestBody Input input){
        return new ResponseEntity<>(filterService.filterByGenre(input.getInput()), HttpStatus.OK);
    }

    @GetMapping("/actor")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByActors(@RequestBody Input input){
        return new ResponseEntity<>(filterService.filterByActor(input.getInput()), HttpStatus.OK);
    }


    @GetMapping("/crew")
    public ResponseEntity<Set<TitleBasicResponse>> getFilmsByCrew(@RequestBody Input input){
        return new ResponseEntity<>(filterService.filterByCrew(input.getInput()), HttpStatus.OK);
    }


}