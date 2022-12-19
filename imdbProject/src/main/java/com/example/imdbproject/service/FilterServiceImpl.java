package com.example.imdbproject.service;

import com.example.imdbproject.model.Aka;
import com.example.imdbproject.model.Genre;
import com.example.imdbproject.model.NameBasic;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.repository.AkaRepository;
import com.example.imdbproject.repository.GenreRepository;
import com.example.imdbproject.repository.NameBasicRepository;
import com.example.imdbproject.repository.TitleBasicRepository;
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
    private NameBasicRepository nameBasic;
    private AkaRepository akaRepository;
    @Override
    public Set<TitleBasicResponse> filterByYear(int year) {

        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();
        Set<TitleBasic> filteredMovies = titleBasicRepository.findByEndYearIs(year);

        for(TitleBasic titleBasic:filteredMovies)
            filteredMoviesSummary.add(titleBasic.responseModel());

        return filteredMoviesSummary;

    }

    @Override
    public Set<TitleBasicResponse> filterByGenre(String genre) {

        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();
        Set<Genre> filteredMovies = genreRepository.findByGenre(genre);

        for(Genre titleBasicByGenre:filteredMovies)
            filteredMoviesSummary.add(titleBasicRepository.findById(titleBasicByGenre.getTitleBasic()).get().responseModel());

        return filteredMoviesSummary;
    }

    @Override
    public Set<TitleBasicResponse> filterByActor(String name) {
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Optional<NameBasic> inputNameBasic = nameBasic.findByPrimaryName(name);
        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();

        filteredMovies = inputNameBasic.get().getKnownForTitles();
        for(TitleBasic titleBasic1:filteredMovies)
            filteredMoviesSummary.add(titleBasic1.responseModel());

        return filteredMoviesSummary;

    }

    @Override
    public Set<TitleBasicResponse> filterByRegion(String region) {

        Set<Aka> inputAka = akaRepository.findByRegion(region);
        Set<TitleBasic> filteredMovies = new HashSet<>();
        Set<TitleBasicResponse> filteredMoviesSummary = new HashSet<>();

        for (Aka aka:inputAka)
            filteredMovies.add(aka.getTitleId());

        for(TitleBasic titleBasic1:filteredMovies)
            filteredMoviesSummary.add(titleBasic1.responseModel());


        return filteredMoviesSummary;

    }
}
