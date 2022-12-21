package com.example.imdbproject.service;

import com.example.imdbproject.exceptions.DuplicateName;
import com.example.imdbproject.exceptions.WrongInput;
import com.example.imdbproject.exceptions.ratingOutOfBound;
import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Rating;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.WatchList;
import com.example.imdbproject.model.response.*;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.transaction.Transactional;


@Slf4j
@AllArgsConstructor
@Service
@Transactional
@Configuration
public class UserServiceImp implements UserService, UserDetailsService {
    private final GenreRepository genreRepository;

    private final NameBasicRepository nameBasicRepository;
    private final RatingRepository ratingRepository;
    private final WatchListRepository watchListRepository;
    private final PrincipalRepository principalRepository;
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
    public BooleanResponse makeWatchList(String name, String username) {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        try {
            if(watchListRepository.findByNameAndOwner(name,user.get()).isPresent())
                return new BooleanResponse(false);
            WatchList newWatchList = WatchList.builder().name(name).owner( user.get()).build();
            watchListRepository.save(newWatchList);
            return new BooleanResponse(true);
        }catch (Exception e) {
            return new BooleanResponse(false);
        }
    }

    @Override
    public BooleanResponse addFilmToWatchList( String watchlistName, String titleBasicId , String username) {

        try {

            AllUser user = allUserRepository.findByUsername(username).get();
            TitleBasic film = titleBasicRepository.findById(titleBasicId).get();

            if (watchListRepository.findByName(watchlistName).isPresent()) {
                if (watchListRepository.findByOwnerAndNameAndTitleBasic(user, watchlistName, film).isPresent())
                    return new BooleanResponse(false);

                WatchList watchList = WatchList.builder().owner(user).
                        name(watchlistName).titleBasic(film).build();
                watchListRepository.save(watchList);
                return new BooleanResponse(true);
            } else
                return new BooleanResponse(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BooleanResponse(false);
        }
    }

    @Override
    public BooleanResponse makeFavouriteList(String name, String username) {
        Optional<AllUser> user = allUserRepository.findByUsername(username);
        BooleanResponse booleanResponse;
        try {
            if(favouriteListRepository.findByOwnerAndName(user.get(),name).isPresent())
                return new BooleanResponse(false);
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

            if (favouriteListRepository.findByName(favouriteListName).isPresent()) {
                if (favouriteListRepository.findByOwnerAndNameAndTitleBasic(user,favouriteListName,film).isPresent())
                    return new BooleanResponse(false);
                else {
                    FavouriteList favouriteList = FavouriteList.builder().owner(user).name(favouriteListName).
                            titleBasic(film).build();
                    favouriteListRepository.save(favouriteList);
                    return new BooleanResponse(true);
                }
            }else
                return new BooleanResponse(false);
        }catch (Exception e) {
            return new BooleanResponse(false);
        }
    }

    @Override
    public ArrayList<FavouriteListResponse> showFavouriteList(String userId) {

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

    public ArrayList<TitleBasicWatchList> showWatchList(String userId) {



        AllUser user = allUserRepository.findByUsername(userId).get();
        Set<WatchList> watchListSet;
        watchListSet = user.getWatchLists();
        ArrayList<TitleBasicWatchList> answer = new ArrayList<>();


        for (WatchList watchList : watchListSet) {
            if (watchList.getTitleBasic() == null) {
                watchListSet.remove(watchList);
                break;
            }
        }



        WatchList[] responseArray = new WatchList[watchListSet.size()];
        int i=0;
        for(WatchList f : watchListSet){
            responseArray[i] = f;
            i++;
        }
        Arrays.sort(responseArray);
        TitleBasicWatchList titleBasicWatchList = new TitleBasicWatchList();
        for(i=0;i<responseArray.length;i++){
            if (i == 0){
                titleBasicWatchList.setName(responseArray[i].getName());
                titleBasicWatchList.setOwnerUsername(userId);
                titleBasicWatchList.getMovieName().add(responseArray[i].getTitleBasic().getPrimaryTitle());

            }
            else if(responseArray[i].getName().equals(responseArray[i-1].getName())){
                titleBasicWatchList.getMovieName().add(responseArray[i].getTitleBasic().getPrimaryTitle());
            } else if (responseArray[i].getName().compareTo(responseArray[i-1].getName())!=0) {
                answer.add(titleBasicWatchList);
                titleBasicWatchList = new TitleBasicWatchList();
                titleBasicWatchList.setName(responseArray[i].getName());
                titleBasicWatchList.getMovieName().add(responseArray[i].getTitleBasic().getPrimaryTitle());
                titleBasicWatchList.setOwnerUsername(userId);

            }
        }
        answer.add(titleBasicWatchList);

        return answer;

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

    @Override
    public ArrayList<TitleBasicRecommenderResponse> recommender(String username) {


        AllUser user = allUserRepository.findByUsername(username).get();
        ArrayList<FavouriteListResponse> favouriteListResponse = showFavouriteList(username);
        Map <String,Integer> favoriteGenres = new HashMap<>();

        for (FavouriteListResponse favouriteList : favouriteListResponse){
            for (String genre : favouriteList.getTitleBasicResponse().getGenres()){

                if (favoriteGenres.containsKey(genre))
                    favoriteGenres.put(genre , favoriteGenres.get(genre)+1);

                else favoriteGenres.put(genre , 1);
            }
        }

        int max1=0,max2=0,max3=0;
        String genre1 = null,genre2 = null,genre3 = null;
        LinkedHashMap <String , Integer> sortedGenres = new LinkedHashMap<>();
        for (Map.Entry <String , Integer> eachGenre : favoriteGenres.entrySet()){
            //sortedGenres.put(eachGenre.getKey() , eachGenre.getValue());
            if(max1 < eachGenre.getValue()){
                max3 = max2;
                genre3 = genre2;
                max2 =max1;
                genre2 = genre1;
                max1 = eachGenre.getValue();
                genre1 = eachGenre.getKey();
            } else if (max2 < eachGenre.getValue() ) {
                max3 = max2;
                genre3 = genre2;
                max2 = eachGenre.getValue();
                genre2 = eachGenre.getKey();
            }else if (max3 < eachGenre.getValue() ) {
                max3 = eachGenre.getValue();
                genre3 = eachGenre.getKey();
            }
        }

//        Set<TitleBasic> genre1Movies = genreRepository.findAllByGenre(genre1);

        Set<Genre> genre1TitleString = genreRepository.findByGenre(genre1);
        Set<Genre> genre2TitleString = genreRepository.findByGenre(genre2);
        Set<Genre> genre3TitleString = genreRepository.findByGenre(genre3);

        Set<TitleBasic> genre1Movies = new HashSet<>();
        Set<TitleBasic> genre2Movies =  new HashSet<>();
        Set<TitleBasic> genre3Movies =  new HashSet<>();


        for(Genre titleBasicByGenre:genre1TitleString)
            genre1Movies.add(titleBasicRepository.findById(titleBasicByGenre.getTitleBasic()).get());

        for(Genre titleBasicByGenre:genre2TitleString)
            genre2Movies.add(titleBasicRepository.findById(titleBasicByGenre.getTitleBasic()).get());

        for(Genre titleBasicByGenre:genre3TitleString)
            genre3Movies.add(titleBasicRepository.findById(titleBasicByGenre.getTitleBasic()).get());




        ArrayList <TitleBasic> responses = new ArrayList<>();

        //for having only one of the genres
        Set <TitleBasic> oneGenre = new HashSet<>();
        oneGenre.addAll(genre1Movies);
        oneGenre.addAll(genre2Movies);
        oneGenre.addAll(genre3Movies);
        //putting rating filter
        for (TitleBasic eachFilm : oneGenre)
            if (eachFilm.getRating().getAverageRate() >= 7.0f && eachFilm.getGenres().size()==1)
                responses.add(eachFilm);

        //for having two of highest the genres
        for (TitleBasic eachFilm : genre2Movies) {
            if (genre1Movies.contains(eachFilm) && eachFilm.getRating().getAverageRate() >= 7.0f
                    && eachFilm.getGenres().size() == 2)
                responses.add(eachFilm);
        }
        //for having all three genres
        for (TitleBasic eachFilm : genre1Movies)
            if (genre2Movies.contains(eachFilm) && genre3Movies.contains(eachFilm)  && eachFilm.getRating().getAverageRate() >= 7.0f)
                responses.add(eachFilm);

        ArrayList<TitleBasicRecommenderResponse> answer = new ArrayList<>();
        for(TitleBasic titleBasic : responses){
            answer.add(titleBasic.toRecommenderResponse());
        }

        if (answer.isEmpty()){

            Set<TitleBasic> latestMovies = titleBasicRepository.findTop10ByOrderByEndYearDesc();
            for(TitleBasic titleBasic: latestMovies)
                answer.add(titleBasic.toRecommenderResponse());
        }
        return answer;

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


    Set<TitleBasicResponse> fillingInfo(Set<TitleBasic> films){

        Set<TitleBasicResponse> allFilms = new HashSet<>();

        for (TitleBasic eachFilm: films){

            Set <PrincipalResponse> casts = new HashSet<>();
            Set <PrincipalResponse> crew = new HashSet<>();
            TitleBasicResponse filmResponse = eachFilm.responseModel();
            //getting all the cast and crew

            Set <Principal> principals = principalRepository.findByFilmCode(eachFilm.getTConst());

            //adding cast and crew
            for (Principal eachPerson : principals){

                if (eachPerson.getNConst() == null)
                    continue;

                if (eachPerson.getCategory().equals("actor")  || eachPerson.getCategory().equals("actress") )
                    casts.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                            , eachPerson.getJob(), eachPerson.getCharacters()));

                else crew.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                        , eachPerson.getJob(), eachPerson.getCharacters()));

            }

            //adding the rate
            filmResponse.setRate(ratingRepository.findByTitleConst(eachFilm).responseModel());

            filmResponse.setCrew( crew);
            filmResponse.setActors(casts);


            //add comments---------------------------------------------------------------------------------
            Set <Comment> comments = commentRepository.findByTitleBasicAndIsReply(eachFilm , false);

            Set <Comment> addingAllSubComments = comments;
            Set <CommentResponse> addingAllSubComments2= CommentResponse.makeCommentRespond(comments);
            Set <CommentResponse> copy = addingAllSubComments2;

            while (! addingAllSubComments.isEmpty()) {
                Set <Comment> newComments = new HashSet<>();
                Set <CommentResponse> newComments1 = new HashSet<>();

                for (CommentResponse comment : addingAllSubComments2) {

                    Comment foundedComment=null;
                    for (Comment comment1 : addingAllSubComments){
                        if (CommentResponse.isEqual(comment1 , comment)){
                            foundedComment = comment1;
                            break;
                        }
                    }
                    Set<Comment> replies = commentRepository.findByReplyForMainComment(foundedComment);

                    comment.setReplies(CommentResponse.makeCommentRespond(replies));


                    newComments.addAll(replies);
                    newComments1.addAll(comment.getReplies());

                }

                addingAllSubComments = newComments;
                addingAllSubComments2 = newComments1;
            }

            filmResponse.setComments(copy);

            allFilms.add(filmResponse);
        }
        return allFilms;
    }


}
