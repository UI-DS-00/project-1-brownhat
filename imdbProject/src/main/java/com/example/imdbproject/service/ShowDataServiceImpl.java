package com.example.imdbproject.service;
import com.example.imdbproject.model.*;
import com.example.imdbproject.model.response.NameBasicSummery;
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

public class ShowDataServiceImpl implements ShowDataService{


    private final TitleBasicRepository titleBasicRepository;
    private final PrimaryProfessionRepository primaryProfessionRepository;
    private final NameBasicRepository nameBasicRepository;

    private final RatingRepository ratingRepository;

    private FavouriteListRepository favouriteListRepository;

    @Override
    public TitleBasicResponse allMoviesData(String tConst) {
        Optional<TitleBasic> titleBasic = titleBasicRepository.findById(tConst);

        if(titleBasic.isEmpty())
            throw new EntityNotFoundException(TitleBasic.class.getName());


        return titleBasic.get().responseModel();

    }

    @Override
    public Set<TitleBasicResponse> allMoviesData() {
        Set<TitleBasicResponse> allFilms = new HashSet<>();

        titleBasicRepository.findAll().forEach(titleBasic ->
                allFilms.add(titleBasic.responseModel()));


        return allFilms;
    }



    @Override
    public Set<NameBasicSummery> ActorsAndDirectors(String professions) {

        Set <PrimaryProfession> primaryProfession = primaryProfessionRepository.findByProfession(professions);
        Set <NameBasicSummery> nameBasics = new HashSet<>();

        for (PrimaryProfession primaryProfession1 :primaryProfession) {
           Optional <NameBasic>  person =  nameBasicRepository.findById(primaryProfession1.getNameBasicBy().getNConst());

           if (person.isPresent())
               nameBasics.add(person.get().responseModel());
        }




        return nameBasics;


    }

    @Override
    public Set<TitleBasicResponse> filmEndYear() {

        Set <TitleBasic> allFilms= titleBasicRepository.findAllByOrderByEndYearDesc();

        Set <TitleBasicResponse> allFilms1 = new HashSet<>();

        for (TitleBasic titleBasic : allFilms)
            allFilms1.add(titleBasic.responseModel());


        return allFilms1;
    }

    @Override
    public Set<TitleBasicResponse> filmRating() {

        Set <Rating> films = ratingRepository.findAllByOrderByAverageRateDesc();

        Set <TitleBasicResponse> allFilms = new HashSet<>();
        for (Rating rating : films)
            allFilms.add(rating.getTitleConst().responseModel());

        return allFilms;

    }

    @Override
    public Set<TitleBasicResponse> topTen() {

        Set<Rating> inputRatings= ratingRepository.findTop10ByOrderByAverageRateDesc();
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Set<TitleBasicResponse> moviesByRating = new HashSet<>();

        for (Rating rating:inputRatings)
            filteredMovies.add(rating.getTitleConst());

        for(TitleBasic titleBasic1:filteredMovies)
            moviesByRating.add(titleBasic1.responseModel());


        return moviesByRating;
    }

    @Override
    public Set<FavouriteList> favouriteList(TitleBasic titleBasic) {

        Set<FavouriteList> favouriteLists;
        favouriteLists = favouriteListRepository.findByList(titleBasic);

        return favouriteLists;
    }


}
