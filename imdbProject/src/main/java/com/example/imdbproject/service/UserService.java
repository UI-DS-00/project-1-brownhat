package com.example.imdbproject.service;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;

import java.util.ArrayList;
import java.util.List;

import com.example.imdbproject.model.response.BooleanResponse;
import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.TitleBasicRecommenderResponse;
import com.example.imdbproject.model.response.TitleBasicResponse;

import java.util.Optional;

public interface UserService {

    Boolean reply(String recommentText, String username,Long commentId);
    void rating (String titleBasic,Float rateAmount , String username);

    BooleanResponse makeWatchList(String username , String filmName);

    BooleanResponse addFilmToWatchList(String name ,String titleBasic , String username);
    BooleanResponse makeFavouriteList(String name , String username);
    BooleanResponse addFilmToFavouriteList(String username, String favouriteListName, String titleBasic);
    void addComment(String userId , String commentText , String titleBasicId);
    Boolean signUp(String username , String password);
    ArrayList <TitleBasicRecommenderResponse> recommender(String username);

    ArrayList<FavouriteListResponse> showFavouriteList(String userId);



    //================================ JWT :)

    AllUser saveUser (AllUser allUser);
    Role saveRole(Role role);
    void addRoleToUser(String username , String roleName);
    Optional<AllUser> getUser(String userName);
    //method of returning a list of all users
     List<AllUser> getUser();


}
