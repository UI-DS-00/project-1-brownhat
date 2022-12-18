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
import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.WatchListResponse;
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
    public Boolean makeWatchList(String name, String username) {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        BooleanResponse booleanResponse;
        try {
            WatchList newWatchList = WatchList.builder().name(name).owner( user.get()).build();
            watchListRepository.save(newWatchList);
            booleanResponse = new BooleanResponse(true);
        }catch (Exception e) {
            booleanResponse = new BooleanResponse(false);
        }
        return booleanResponse.getResponse();
    }

    @Override
    public Boolean addFilmToWatchList( String watchlistName, String titleBasicId , String username) {

        try {

            AllUser user = allUserRepository.findByUsername(username).get();
            TitleBasic film = titleBasicRepository.findById(titleBasicId).get();

            WatchList watchList = WatchList.builder().owner(user).
                    name(watchlistName).titleBasic(film).build();
            watchListRepository.save(watchList);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public BooleanResponse makeFavouriteList(String name, String username) {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        BooleanResponse booleanResponse;
        try {
            FavouriteList newFavouriteList = FavouriteList.builder().name(name).owner( user.get()).build();
            favouriteListRepository.save(newFavouriteList);
            booleanResponse = new BooleanResponse(true);
        }catch (Exception e) {
            booleanResponse = new BooleanResponse(false);
        }
        return booleanResponse;

    }

    @Override
    public BooleanResponse addFilmToFavouriteList(String username, String favouriteListName, String titleBasicId) {

        try {

            AllUser user = allUserRepository.findByUsername(username).get();
            TitleBasic film = titleBasicRepository.findById(titleBasicId).get();

            FavouriteList favouriteList = FavouriteList.builder().owner(user).name(favouriteListName).
                    titleBasic(film).build();
            favouriteListRepository.save(favouriteList);
            return new BooleanResponse(false);
        }catch (Exception e) {
            return new BooleanResponse(true);
        }
    }

    @Override
    public ArrayList<FavouriteListResponse> showPersonalFavouriteList(String userId) {

        AllUser user = allUserRepository.findByUsername(userId).get();
        Set<FavouriteList> favouriteLists;
        favouriteLists = user.getFavoriteLists();
        ArrayList<FavouriteListResponse> favouriteListResponseSet = new ArrayList<>();
        for (FavouriteList favouriteList : favouriteLists) {
            if (favouriteList.getTitleBasic() == null)
                continue;
            favouriteListResponseSet.add(favouriteList.toResponse(favouriteList));
        }

        FavouriteListResponse[] responseArray = new FavouriteListResponse[favouriteListResponseSet.size()];
        int i=0;
        for(FavouriteListResponse f : favouriteListResponseSet){
            responseArray[i] = f;
            i++;
        }
        Arrays.sort(responseArray);
        favouriteListResponseSet = new ArrayList<>();


        for(i=0;i<responseArray.length;i++){
            favouriteListResponseSet.add(responseArray[i]);
        }

        return favouriteListResponseSet;

    }








    public Set<WatchListResponse> showWatchList(String userId) {

        AllUser user = allUserRepository.findByUsername(userId).get();
        Set<WatchList> watchListSet;
        watchListSet = user.getWatchLists();
        Set<WatchListResponse> watchListResponses = new HashSet<>();
        for (WatchList watchList : watchListSet) {
            if (watchList.getTitleBasic() == null)
                continue;
            watchListResponses.add(watchList.toResponse(watchList));
        }

        WatchListResponse[] responseArray = new WatchListResponse[watchListResponses.size()];
        int i=0;
        for(WatchListResponse f : watchListResponses){
            responseArray[i] = f;
            i++;
        }
        Arrays.sort(responseArray);
        watchListResponses = new HashSet<>();


        for(i=0;i<responseArray.length;i++){
            watchListResponses.add(responseArray[i]);
        }

        return watchListResponses;

    }



    @Override
    public Boolean reply(String reCommentText, String username,Long commentId) {

        try {

            Comment reComment = new Comment();
            reComment.setText(reCommentText);
            reComment.setIsReply(true);

            AllUser user = allUserRepository.findByUsername(username).get();
            Comment main = commentRepository.findById(commentId).get();
            reComment.setReplyForMainComment(main);
            reComment.setTitleBasic(main.getTitleBasic());
            reComment.setUser(main.getUser());
            main.getReplies().add(reComment);
            commentRepository.save(reComment);
            commentRepository.save(main);
            user.getComments().add(reComment);
            allUserRepository.save(user);
            return true;

        }catch (Exception e){
            return false;
        }
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

        addRoleToUser(username,"ROLE_USER");

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
