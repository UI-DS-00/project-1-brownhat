package com.example.imdbproject.service;
import com.example.imdbproject.model.NameBasic;
import com.example.imdbproject.model.PrimaryProfession;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.repository.NameBasicRepository;
import com.example.imdbproject.repository.PrimaryProfessionRepository;
import com.example.imdbproject.repository.TitleBasicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
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



}
