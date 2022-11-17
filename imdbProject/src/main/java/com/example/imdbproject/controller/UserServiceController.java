package com.example.imdbproject.controller;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;
import com.example.imdbproject.model.request.RoleToUserForm;
import com.example.imdbproject.service.UserService;
import com.example.imdbproject.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController

@AllArgsConstructor
@RequestMapping ("/loggedIn")

public class UserServiceController {


    private final UserService userService;
    private final UserService userServiceImp;

    @PostMapping ("/rating")
    public void makeRating(@RequestBody String titleBasic,@RequestBody Float ratingAmount)
    {
        userService.rating(titleBasic , ratingAmount);
    }

    @PostMapping ("/make/watchlist/{userId}")
    public void makeWatchList(@RequestBody String name , @PathVariable Integer userId)
    {
        userService.makeWatchList(name , userId);
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
        return ResponseEntity.ok().body(userService.getUser());
        //return new ResponseEntity<>(userServiceImp.getUser(), HttpStatus.OK);
    }

    @PostMapping ("user/save")
    public ResponseEntity <AllUser> saveUser (@RequestBody AllUser user){

        //creating uri to send data to the url and change it to a string
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/loggedIn/user/save")
                .toUriString());

        //this uri is going to be added to the header of the new user header
        return ResponseEntity.created(uri).body(userService.saveUser(user));

    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/loggedIn/role/save")
                .toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }



    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){

        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        //we wrote it down in two lines , using build instead of body , because
        // the addRoleToUser function output is void
        return ResponseEntity.ok().build();
    }


}