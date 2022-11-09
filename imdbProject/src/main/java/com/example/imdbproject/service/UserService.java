package com.example.imdbproject.service;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.TitleBasic;

import java.util.Set;

public interface UserService {

    void rating (String titleBasic,Float rateAmount);

    void makeWatchList(String name , Integer userId);

    void addFilmToWatchList(Integer userId,String name ,String titleBasic);

    void addComment(Integer userId , String commentText , String titleBasicId);
}
