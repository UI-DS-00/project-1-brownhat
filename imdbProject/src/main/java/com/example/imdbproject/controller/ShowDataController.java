package com.example.imdbproject.controller;


import com.example.imdbproject.model.request.Input;
import com.example.imdbproject.model.response.*;
import com.example.imdbproject.service.ShowDataServiceImpl;
import com.example.imdbproject.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/data/show")
public class ShowDataController {
    private final ShowDataServiceImpl showDataService;
    private final UserServiceImp userServiceImp;

    @GetMapping("/allFilms")
    public ResponseEntity<Set<TitleBasicResponse>> getFilms(){
        return new ResponseEntity<>(showDataService.allMoviesData(), HttpStatus.OK);
    }


    @GetMapping("/each/film")
    public ResponseEntity<TitleBasicResponse> getEachFilm(@RequestBody Input filmName){
        return new ResponseEntity<>(showDataService.eachFilmData(filmName.getInput()), HttpStatus.OK);
    }


    @GetMapping("/each/film/favoriteLists/{filmName}")
    public ResponseEntity< Set <TitleBasicFavouriteList>> getOthersFavoriteListsByFilm(@PathVariable("filmName") String film_name){
        return new ResponseEntity<>(showDataService.othersFavouriteList(film_name),HttpStatus.ACCEPTED);
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
    public ResponseEntity<Set<TitleBasicFavouriteList>> getFavouriteLists(@RequestBody Input input) {
        return new ResponseEntity<>(showDataService.othersFavouriteList(input.getInput()),HttpStatus.OK);
        //return new ResponseEntity<>(showDataService.othersFavouriteList("tt0012349"),HttpStatus.OK);
    }
    @GetMapping ("/user/showFavouriteList")
    public ResponseEntity<ArrayList<FavouriteListResponse>> showFavouriteList(Authentication authentication) {
        return new ResponseEntity<>(userServiceImp.showFavouriteList(authentication.getName()),HttpStatus.OK);
    }

    @GetMapping ("/user/showWatchList")
    public ResponseEntity<ArrayList<TitleBasicWatchList>> showWatchList(Authentication authentication) {
        return new ResponseEntity<>(userServiceImp.showWatchList(authentication.getName()),HttpStatus.OK);
    }

    @GetMapping("/user/recommender")
    public ResponseEntity <ArrayList<TitleBasicRecommenderResponse>> recommender(Authentication authentication){
        return new ResponseEntity<>(userServiceImp.recommender(authentication.getName()) , HttpStatus.OK);
    }

    @GetMapping("/topTen")
    public ResponseEntity<ArrayList<TitleBasicResponse>> showTopTen(){
        return new ResponseEntity<>(showDataService.topTen() , HttpStatus.OK);
    }

}

