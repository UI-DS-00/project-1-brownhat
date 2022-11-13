package com.example.imdbproject.controller;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.service.UserService;
import com.example.imdbproject.service.UserService;
import com.example.imdbproject.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@AllArgsConstructor
@RequestMapping ("/loggedIn")

public class UserServiceController {


    private final UserService userServiceImp;


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


    @PostMapping("add/new/comment/{titleBasic}/{id}")
    public void addComment(@RequestBody String commentText , @PathVariable String titleBasic ,@PathVariable Integer id ){

        userServiceImp.addComment(id , commentText , titleBasic);

    }


    //--------------------------------------------------------------------------JWT :)

    @GetMapping("/all/users")
    public ResponseEntity<List<AllUser>> getUsers(){
        return ResponseEntity.ok().body(userServiceImp.getUser());
        //return new ResponseEntity<>(userServiceImp.getUser(), HttpStatus.OK);
    }

}