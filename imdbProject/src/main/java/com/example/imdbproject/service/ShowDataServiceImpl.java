package com.example.imdbproject.service;
import com.example.imdbproject.model.*;
import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.PrincipalResponse;
import com.example.imdbproject.model.response.TitleBasicResponse;
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
    private final AllUserRepository allUserRepository;


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


            //add comments
            Set<Comment> comments = commentRepository.findByTitleBasic(eachFilm);
            Set<Comment> replies;//--------------------------------------------


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
    public Set<TitleBasicResponse> topTen() {

        Set<Rating> inputRatings = ratingRepository.findTop10ByOrderByAverageRateDesc();
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Set<TitleBasicResponse> moviesByRating = new HashSet<>();

        for (Rating rating : inputRatings)
            filteredMovies.add(rating.getTitleConst());

        for (TitleBasic titleBasic1 : filteredMovies)
            moviesByRating.add(titleBasic1.responseModel());


        return moviesByRating;
    }


    @Override
    public Set<FavouriteListResponse> othersFavouriteList(String titleBasicId) {

        TitleBasic titleBasic = titleBasicRepository.findById(titleBasicId).get();
        Set<FavouriteList> favouriteLists;
        favouriteLists = titleBasic.getFavouriteLists();
        System.out.println(favouriteLists);
        Set<FavouriteListResponse> favouriteListResponseSet = new HashSet<>();
        for (FavouriteList favouriteList : favouriteLists) {
            favouriteListResponseSet.add(favouriteList.toResponse(favouriteList));
        }

//        System.out.println(favouriteLists);
        return favouriteListResponseSet;
    }

    @Override
    public Set<FavouriteListResponse> showPersonalFavouriteList(String userId) {

        AllUser user = allUserRepository.findByUsername(userId).get();

        Set<FavouriteList> favouriteLists = user.getFavoriteLists();

//        Set<FavouriteList> favouriteLists = favouriteListRepository.findAllByOwner(user);

        Set<FavouriteListResponse> favouriteListResponseSet = new HashSet<>();
        for (FavouriteList favouriteList : favouriteLists) {
            favouriteListResponseSet.add(favouriteList.toResponse(favouriteList));
        }

        return favouriteListResponseSet;
    }
}

//    public Set<FavouriteListResponse> showPersonalFavouriteList(String userId){
//
//
//        re
//    }
