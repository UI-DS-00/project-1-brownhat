package com.example.imdbproject.service;

import com.example.imdbproject.exceptions.DuplicateName;
import com.example.imdbproject.exceptions.WrongInput;
import com.example.imdbproject.exceptions.ratingOutOfBound;
import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Rating;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.WatchList;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import com.example.imdbproject.model.*;


@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImp implements UserService{


    private RatingRepository ratingRepository;
    private WatchListRepository watchListRepository;

    private FavouriteListRepository favouriteListRepository;
    private CommentRepository commentRepository;
    private TitleBasicRepository titleBasicRepository;
    private AllUserRepository allUserRepository;

    @Override
    public void rating(String titleBasic, Float rateAmount) {


        if (rateAmount > 10 || rateAmount < 0)
            throw new ratingOutOfBound();

        Optional <Rating> changeRate = ratingRepository.findById(titleBasic);


        Rating rating = null;

        if (changeRate.isPresent())
            rating=changeRate.get();

        rating.calculateAverage(rateAmount);

        ratingRepository.save(rating);
    }


    @Override
    public void makeWatchList(String name , Integer userId) {

        Optional<AllUser> user = allUserRepository.findById(userId);

        try {
            WatchList newWatchList = new WatchList(name , user.get() , new HashSet<>());
            watchListRepository.save(newWatchList);

        }catch (Exception e) {
            throw new DuplicateName();
        }

    }

    @Override
    public void addFilmToWatchList(Integer userId, String watchlistName, String titleBasic) {

        Optional <WatchList> currentWatchList = watchListRepository.findByName(watchlistName);
        Optional <AllUser> user = allUserRepository.findById(userId);
        Optional <TitleBasic> film = titleBasicRepository.findById(titleBasic);

        if (currentWatchList.isEmpty())
            throw new WrongInput("watch list not found");
        if (film.isEmpty())
            throw new WrongInput("film not found");

        currentWatchList.get().getList().add(film.get());
        watchListRepository.save(currentWatchList.get());

    }

    @Override
    public void makeFavouriteList(String name, Integer userId) {
        Optional<AllUser> user = allUserRepository.findById(userId);
        try {
            FavouriteList newWatchList = new FavouriteList(name , user.get() , new HashSet<>());
            favouriteListRepository.save(newWatchList);

        }catch (Exception e) {
            throw new DuplicateName();
        }
    }

    @Override
    public void addFilmToFavouriteList(Integer userId, String name, String titleBasic) {
        Optional <FavouriteList> currentWatchList = favouriteListRepository.findByName(name);
        Optional <AllUser> user = allUserRepository.findById(userId);
        Optional <TitleBasic> film = titleBasicRepository.findById(titleBasic);

        if (currentWatchList.isEmpty())
            throw new WrongInput("watch list not found");
        if (film.isEmpty())
            throw new WrongInput("film not found");

        currentWatchList.get().getList().add(film.get());
        favouriteListRepository.save(currentWatchList.get());
    }

    @Override
    public Optional<Comment> reply(Comment comment, Comment reComment) {

        commentRepository.save(reComment);
        comment.getReplies().add(reComment);
        commentRepository.save(comment);

        return Optional.empty();
    }

    @Override
    public void addComment(Integer userId, String commentText, String titleBasicId) {

        Optional<AllUser> user = allUserRepository.findById(userId);
        Optional <TitleBasic> movie = titleBasicRepository.findById(titleBasicId);

        if (user.isEmpty() || movie.isEmpty())
            throw new WrongInput("wrong user id or movie id");

        Comment newComment= new Comment(user.get() , movie.get() ,commentText);

        commentRepository.save(newComment);
    }


}
