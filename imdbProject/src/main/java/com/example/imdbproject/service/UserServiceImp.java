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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.imdbproject.model.*;

import javax.transaction.Transactional;


@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class UserServiceImp implements UserService{


    private final RatingRepository ratingRepository;
    private final WatchListRepository watchListRepository;

    private final FavouriteListRepository favouriteListRepository;
    private final CommentRepository commentRepository;
    private final TitleBasicRepository titleBasicRepository;
    private final AllUserRepository allUserRepository;

    private final RoleRepository roleRepository;

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


    //----------------------------------------------------------------------------------------JWT :)
    @Override
    public AllUser saveUser(AllUser allUser) {

        log.info("saving new user to the database");

        return allUserRepository.save(allUser);
    }

    @Override
    public Role saveRole(Role role) {

        log.info("saving new role to the database");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        log.info("adding role to the user");

        Optional<AllUser> allUser = allUserRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        allUser.get().getRoles().add(role.get());
    }

    @Override
    public Optional<AllUser> getUser(String userName) {

        log.info("getting a specific user username: {} form the database", userName);
        return allUserRepository.findByUsername(userName);
    }

    @Override
    public List<AllUser> getUser() {

        log.info("getting all users from the database");
        System.out.println("\n\n hello");
        List<AllUser> allUserList;
        allUserList =  allUserRepository.findAll();
        return allUserList;
    }

}
