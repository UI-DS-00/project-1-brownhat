package com.example.imdbproject.service;
import com.example.imdbproject.model.*;
import com.example.imdbproject.model.response.*;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service

public class ShowDataServiceImpl implements ShowDataService {


    private final TitleBasicRepository titleBasicRepository;
    private final PrimaryProfessionRepository primaryProfessionRepository;
    private final NameBasicRepository nameBasicRepository;
    private final RatingRepository ratingRepository;
    private FavouriteListRepository favouriteListRepository;

    private PrincipalRepository principalRepository;
    private CommentRepository commentRepository;

    @Override
    public TitleBasicResponse allMoviesData(String tConst) {
        Optional<TitleBasic> titleBasic = titleBasicRepository.findById(tConst);

        if (titleBasic.isEmpty())
            throw new EntityNotFoundException(TitleBasic.class.getName());


        return titleBasic.get().responseModel();

    }


    @Override
    public TitleBasicResponse eachFilmData(String filmName) {

        Optional <TitleBasic> eachFilm = titleBasicRepository.findByPrimaryTitle(filmName);

        if (eachFilm.isEmpty())
            throw new NoSuchElementException("no such film was found");

        Set <PrincipalResponse> casts = new HashSet<>();
        Set <PrincipalResponse> crew = new HashSet<>();
        TitleBasicResponse filmResponse = eachFilm.get().responseModel();
        //getting all the cast and crew

        Set <Principal> principals = principalRepository.findByFilmCode(eachFilm.get().getTConst());

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
        filmResponse.setRate(ratingRepository.findByTitleConst(eachFilm.get()).responseModel());

        filmResponse.setCrew( crew);
        filmResponse.setActors(casts);


        //add comments---------------------------------------------------------------------------------
        Set <Comment> comments = commentRepository.findByTitleBasicAndIsReply(eachFilm.get() , false);

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

        return filmResponse;
    }

    @Override
    public Set<TitleBasicResponse> allMoviesData() {

        Set<TitleBasicResponse> allFilms = new HashSet<>();
        Iterable<TitleBasic> films = titleBasicRepository.findAll();


        for (TitleBasic eachFilm : films) {

            Set<PrincipalResponse> casts = new HashSet<>();
            Set<PrincipalResponse> crew = new HashSet<>();
            TitleBasicResponse filmResponse = eachFilm.responseModel();
            //getting all the cast and crew

            Set<Principal> principals = principalRepository.findByFilmCode(eachFilm.getTConst());

            //adding cast and crew
            for (Principal eachPerson : principals) {

                if (eachPerson.getNConst() == null)
                    continue;

                if (eachPerson.getCategory().equals("actor") || eachPerson.getCategory().equals("actress"))
                    casts.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                            , eachPerson.getJob(), eachPerson.getCharacters()));

                else
                    crew.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                            , eachPerson.getJob(), eachPerson.getCharacters()));

            }

            //adding the rate
            filmResponse.setRate(ratingRepository.findByTitleConst(eachFilm).responseModel());

            filmResponse.setCrew(crew);
            filmResponse.setActors(casts);


            //add comments---------------------------------------------------------------------------------
            Set<Comment> comments = commentRepository.findByTitleBasicAndIsReply(eachFilm, false);

            Set<Comment> addingAllSubComments = comments;
            Set<CommentResponse> addingAllSubComments2 = CommentResponse.makeCommentRespond(comments);
            Set<CommentResponse> copy = addingAllSubComments2;

            while (!addingAllSubComments.isEmpty()) {
                Set<Comment> newComments = new HashSet<>();
                Set<CommentResponse> newComments1 = new HashSet<>();

                for (CommentResponse comment : addingAllSubComments2) {

                    Comment foundedComment = null;
                    for (Comment comment1 : addingAllSubComments) {
                        if (CommentResponse.isEqual(comment1, comment)) {
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


    @Override
    public Set<NameBasicSummery> ActorsAndDirectors(String professions) {

        Set<PrimaryProfession> primaryProfession = primaryProfessionRepository.findByProfession(professions);
        Set<NameBasicSummery> nameBasics = new HashSet<>();

        for (PrimaryProfession primaryProfession1 : primaryProfession) {
            Optional<NameBasic> person = nameBasicRepository.findById(primaryProfession1.getNameBasic().getNConst());

            if (person.isPresent())
                nameBasics.add(person.get().responseModel());
        }
        return nameBasics;


    }

    @Override
    public Set<TitleBasicResponse> filmEndYear() {

        Set<TitleBasic> allFilms = titleBasicRepository.findAllByOrderByEndYearDesc();

        Set<TitleBasicResponse> allFilms1 = new HashSet<>();

        for (TitleBasic titleBasic : allFilms)
            allFilms1.add(titleBasic.responseModel());


        return allFilms1;
    }

    @Override
    public Set<TitleBasicResponse> filmRating() {

        Set<Rating> films = ratingRepository.findAllByOrderByAverageRateDesc();

        Set<TitleBasicResponse> allFilms = new HashSet<>();
        for (Rating rating : films)
            allFilms.add(rating.getTitleConst().responseModel());

        return allFilms;

    }

    @Override
    public ArrayList<TitleBasicResponse> topTen() {

        ArrayList<Rating> inputRatings = ratingRepository.findTop10ByOrderByAverageRateDesc();
        ArrayList<TitleBasic> filteredMovies = new ArrayList<>();
        ArrayList<TitleBasicResponse> moviesByRating = new ArrayList<>();

        for (Rating rating : inputRatings)
            filteredMovies.add(rating.getTitleConst());


        for (TitleBasic eachFilm : filteredMovies) {

            Set<PrincipalResponse> casts = new HashSet<>();
            Set<PrincipalResponse> crew = new HashSet<>();
            TitleBasicResponse filmResponse = eachFilm.responseModel();
            //getting all the cast and crew

            Set<Principal> principals = principalRepository.findByFilmCode(eachFilm.getTConst());

            //adding cast and crew
            for (Principal eachPerson : principals) {

                if (eachPerson.getNConst() == null)
                    continue;

                if (eachPerson.getCategory().equals("actor") || eachPerson.getCategory().equals("actress"))
                    casts.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                            , eachPerson.getJob(), eachPerson.getCharacters()));

                else
                    crew.add(new PrincipalResponse(nameBasicRepository.findById(eachPerson.getNConst().getNConst()).get().responseModel()
                            , eachPerson.getJob(), eachPerson.getCharacters()));

            }

            //adding the rate
            filmResponse.setRate(ratingRepository.findByTitleConst(eachFilm).responseModel());

            filmResponse.setCrew(crew);
            filmResponse.setActors(casts);


            //add comments---------------------------------------------------------------------------------
            Set<Comment> comments = commentRepository.findByTitleBasicAndIsReply(eachFilm, false);

            Set<Comment> addingAllSubComments = comments;
            Set<CommentResponse> addingAllSubComments2 = CommentResponse.makeCommentRespond(comments);
            Set<CommentResponse> copy = addingAllSubComments2;

            while (!addingAllSubComments.isEmpty()) {
                Set<Comment> newComments = new HashSet<>();
                Set<CommentResponse> newComments1 = new HashSet<>();

                for (CommentResponse comment : addingAllSubComments2) {

                    Comment foundedComment = null;
                    for (Comment comment1 : addingAllSubComments) {
                        if (CommentResponse.isEqual(comment1, comment)) {
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

            moviesByRating.add(filmResponse);
        }


        return moviesByRating;
    }


    @Override
    public Set<TitleBasicFavouriteList> othersFavouriteList(String primaryTitle) {

        Set<FavouriteList> favouriteLists = favouriteListRepository.findByTitleBasic
                (titleBasicRepository.findByPrimaryTitle(primaryTitle).get());

        Set<TitleBasicFavouriteList> answer = new HashSet<>();
        Set<FavouriteList> temp;
        for (FavouriteList favouriteList : favouriteLists) {
            if (favouriteList.getTitleBasic() == null) {
                continue;
            }
            temp = favouriteListRepository.findAllByNameAndOwner(favouriteList.getName(), favouriteList.getOwner());
                for (FavouriteList tempCheck:temp) {
                    if (tempCheck.getTitleBasic() == null) {
                        temp.remove(tempCheck);
                        break;
                    }
                }
                answer.add(FavouriteList.showList(temp));

        }
        return answer;
    }
}
