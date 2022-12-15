package com.example.imdbproject.service;

import com.example.imdbproject.exceptions.DuplicateName;
import com.example.imdbproject.exceptions.WrongInput;
import com.example.imdbproject.exceptions.ratingOutOfBound;
import com.example.imdbproject.filter.CostumeAuthenticationFilter;
import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Rating;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.WatchList;
import com.example.imdbproject.model.response.BooleanResponse;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.example.imdbproject.model.*;

import javax.swing.*;
import javax.transaction.Transactional;


@Slf4j
@AllArgsConstructor
@Service
@Transactional
@Configuration
public class UserServiceImp implements UserService, UserDetailsService {


    private final RatingRepository ratingRepository;
    private final WatchListRepository watchListRepository;

    private final FavouriteListRepository favouriteListRepository;
    private final CommentRepository commentRepository;
    private final TitleBasicRepository titleBasicRepository;
    private final AllUserRepository allUserRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    //this is where spring is going to load users from the database
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        if (user == null){
            log.error("user not found");
            throw new UsernameNotFoundException("user not found");
        }else {
            log.info("user found in the db");
        }
        //return null;
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // we are looping through all the roles of the user we found , and for each role , we are going to
        //give them a simple granted authority

        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.get().getUsername(),user.get().getPassword(),authorities);

        //authorities : we are passing all of our rules and permissions of our application up above
    }

    @Override
    public void rating(String titleBasic, Float rateAmount , String username) {

        Optional <AllUser> user=allUserRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("you are not login");

        if (rateAmount > 10 || rateAmount < 0)
            throw new ratingOutOfBound();

        Optional <TitleBasic> film = titleBasicRepository.findById(titleBasic);
        if (film.isEmpty())
            throw new WrongInput("film not found");


        //if the user has not rated yet
        if (user.get().getRatingFilms().contains(film.get()))
            throw new DuplicateName();

        Rating changeRate = ratingRepository.findByTitleConst(film.get());


        changeRate.calculateAverage(rateAmount);
        ratingRepository.save(changeRate);

        user.get().getRatingFilms().add(film.get());

    }


    @Override
    public void makeWatchList(String username , String filmName) {

        Optional<AllUser> user = allUserRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user not found");

        try {
            WatchList newWatchList = new WatchList(filmName , user.get() , new HashSet<>());
            watchListRepository.save(newWatchList);

        }catch (Exception e) {
            throw new DuplicateName();
        }

    }

    @Override
    public void addFilmToWatchList( String watchlistName, String titleBasic , String username) {

        Optional <AllUser> user = allUserRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user not found");
        Optional <WatchList> currentWatchList = watchListRepository.findByNameAndOwner(watchlistName , user.get());
        Optional <TitleBasic> film = titleBasicRepository.findById(titleBasic);

        if (currentWatchList.isEmpty())
            throw new WrongInput("watch list not found");
        if (film.isEmpty())
            throw new WrongInput("film not found");

        currentWatchList.get().getList().add(film.get());
        watchListRepository.save(currentWatchList.get());
    }

    @Override
    public BooleanResponse makeFavouriteList(String name, String username) {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        BooleanResponse booleanResponse;
        try {
            FavouriteList newWatchList = new FavouriteList(name , user.get() , new HashSet<>());
            favouriteListRepository.save(newWatchList);
            booleanResponse = new BooleanResponse(true);
        }catch (Exception e) {
            booleanResponse = new BooleanResponse(false);
            throw new DuplicateName();
        }
        return new BooleanResponse(booleanResponse.getResponse());

    }

    @Override
    public BooleanResponse addFilmToFavouriteList(String username, String favouriteListName, String titleBasic) {
        try {
            AllUser user = allUserRepository.findByUsername(username).get();
            TitleBasic film = titleBasicRepository.findById(titleBasic).get();

            for (FavouriteList f : user.getFavoriteLists()) {
                if (favouriteListName.compareTo(f.getName()) == 0) {
                    f.getList().add(film);
                    favouriteListRepository.save(f);
                    user.getFavoriteLists().add(f);
                    allUserRepository.save(user);
                    return new BooleanResponse(true);

                }
            }

        }catch (Exception e){
            return new BooleanResponse(false);
        }
        return new BooleanResponse(false);
    }

    @Override
    public Optional<Comment> reply(Comment comment, Comment reComment) {

        commentRepository.save(reComment);
        comment.getReplies().add(reComment);
        commentRepository.save(comment);

        return Optional.empty();
    }

    @Override
    public void addComment(String username, String commentText, String titleBasicId) {

        Optional<AllUser> user = allUserRepository.findByUsername(username);
        Optional <TitleBasic> movie = titleBasicRepository.findById(titleBasicId);

        if (user.isEmpty() || movie.isEmpty())
            throw new WrongInput("wrong user id or movie id");

        Comment newComment= new Comment(user.get() , movie.get() ,commentText);

        commentRepository.save(newComment);
    }

    @Override
    public Boolean signUp(String username, String password) {


        Optional<AllUser> user = allUserRepository.findByUsername(username);

        if (user.isPresent())
            throw new DuplicateName();

        AllUser newUser = new AllUser(username , password);

        saveUser(newUser);

        return true;
    }


    //----------------------------------------------------------------------------------------JWT :)
    @Override
    public AllUser saveUser(AllUser allUser) {

        log.info("saving new user to the database");
        allUser.setPassword(passwordEncoder.encode(allUser.getPassword()));
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
        List<AllUser> allUserList;
        allUserList =  allUserRepository.findAll();
        return allUserList;
    }
}
