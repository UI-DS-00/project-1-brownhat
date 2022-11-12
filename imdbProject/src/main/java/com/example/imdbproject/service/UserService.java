package com.example.imdbproject.service;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;
import com.example.imdbproject.model.TitleBasic;

import java.util.List;
import java.util.Set;
import com.example.imdbproject.model.Comment;

import java.util.Optional;

public interface UserService {

    Optional<Comment> reply(Comment comment, Comment reComment);
    void rating (String titleBasic,Float rateAmount);

    void makeWatchList(String name , Integer userId);

    void addFilmToWatchList(Integer userId,String name ,String titleBasic);
    void makeFavouriteList(String name , Integer userId);
    void addFilmToFavouriteList(Integer userId,String name ,String titleBasic);


    void addComment(Integer userId , String commentText , String titleBasicId);

    //================================ JWT :)

    AllUser saveUser (AllUser allUser);

    Role saveRole(Role role);

    void addRoleToUser(String username , String roleName);

    Optional<AllUser> getUser(String userName);


    //method of returning a list of all users
     List<AllUser> getUser();
}
