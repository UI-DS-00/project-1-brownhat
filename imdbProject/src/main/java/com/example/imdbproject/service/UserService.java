package com.example.imdbproject.service;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;
import com.example.imdbproject.model.TitleBasic;

import java.util.List;
import java.util.Set;
import com.example.imdbproject.model.Comment;
import com.example.imdbproject.model.response.BooleanResponse;

import java.util.Optional;

public interface UserService {

    Optional<Comment> reply(Comment comment, Comment reComment);
    void rating (String titleBasic,Float rateAmount , String username);

    void makeWatchList(String username , String filmName);

    void addFilmToWatchList(String name ,String titleBasic , String username);
    BooleanResponse makeFavouriteList(String name , String username);
    BooleanResponse addFilmToFavouriteList(Integer userId,String name ,String titleBasic);
    void addComment(String userId , String commentText , String titleBasicId);
    Boolean signUp(String username , String password);

    //================================ JWT :)

    AllUser saveUser (AllUser allUser);

    Role saveRole(Role role);

    void addRoleToUser(String username , String roleName);

    Optional<AllUser> getUser(String userName);


    //method of returning a list of all users
     List<AllUser> getUser();
}
