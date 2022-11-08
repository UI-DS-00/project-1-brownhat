package com.example.imdbproject.controller;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.service.UserService;
import com.example.imdbproject.service.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserServiceController {


    UserServiceImp userServiceImp;


    @PostMapping ("/rating")
    public void makeRating(@RequestBody String titleBasic,@RequestBody Float ratingAmount)
    {
        userServiceImp.rating(titleBasic , ratingAmount);
    }

    @PostMapping ("/make/watchlist/{userId}")
    public void makeWatchList(@RequestBody String name , @PathVariable Integer userId)
    {
        userServiceImp.makeWatchList(name , userId);
    }

    @PostMapping("/add/to/watchlist/{id}")
    public void addToWatchList(@RequestBody String filmId , @RequestBody String watchlistName , @PathVariable Integer id)
    {
        userServiceImp.addFilmToWatchList(id , watchlistName , filmId);
    }
}