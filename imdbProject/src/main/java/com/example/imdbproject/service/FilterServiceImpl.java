package com.example.imdbproject.service;

import com.example.imdbproject.model.*;
import com.example.imdbproject.model.response.BooleanResponse;
import com.example.imdbproject.model.response.CommentResponse;
import com.example.imdbproject.model.response.PrincipalResponse;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service

public class FilterServiceImpl implements FilterService{
    private final GenreRepository genreRepository;
    private TitleBasicRepository titleBasicRepository;
    private NameBasicRepository nameBasicRepository;
    private AkaRepository akaRepository;

    private RatingRepository ratingRepository;
    private CommentRepository commentRepository;
    private PrincipalRepository principalRepository;
    @Override
    public Set<TitleBasicResponse> filterByYear(int year) {

        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();
        Set<TitleBasic> filteredMovies = titleBasicRepository.findByEndYearIs(year);

        filteredMoviesSummary = fillingInfo(filteredMovies);

        return filteredMoviesSummary;

    }

    @Override
    public Set<TitleBasicResponse> filterByGenre(String genre) {


        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();
        Set<TitleBasic> titleBasicSet = new HashSet<>();
        Set<Genre> filteredMovies = genreRepository.findByGenre(genre);

        for(Genre titleBasicByGenre:filteredMovies)
            titleBasicSet.add(titleBasicRepository.findById(titleBasicByGenre.getTitleBasic()).get());

        filteredMoviesSummary = fillingInfo(titleBasicSet);

        return filteredMoviesSummary;
    }

    @Override
    public Set<TitleBasicResponse> filterByActor(String name) {
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Optional<NameBasic> inputNameBasic = nameBasicRepository.findByPrimaryName(name);
        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();

        filteredMovies = inputNameBasic.get().getKnownForTitles();



        filteredMoviesSummary = fillingInfo(filteredMovies);

        Set <TitleBasicResponse> result = new HashSet<>();
        Boolean founded = false;
        for (TitleBasicResponse eachFilm : filteredMoviesSummary){
            for ( PrincipalResponse person : eachFilm.getActors()){
                if (person.getNameBasicSummery().getPrimaryName().equals(inputNameBasic.get().getPrimaryName()))
                    founded = true;
            }
            if (founded)
                result.add(eachFilm);
            founded = false;

        }


        return result;

    }



    @Override
    public Set<TitleBasicResponse> filterByCrew(String name) {
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Optional<NameBasic> inputNameBasic = nameBasicRepository.findByPrimaryName(name);
        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();

        filteredMovies = inputNameBasic.get().getKnownForTitles();



        filteredMoviesSummary = fillingInfo(filteredMovies);

        Set <TitleBasicResponse> result = new HashSet<>();
        Boolean founded = false;
        for (TitleBasicResponse eachFilm : filteredMoviesSummary){
            for ( PrincipalResponse person : eachFilm.getCrew()){
                if (person.getNameBasicSummery().getPrimaryName().equals(inputNameBasic.get().getPrimaryName()))
                    founded = true;
            }
            if (founded)
                result.add(eachFilm);
            founded = false;

        }


        return result;

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
