package com.example.imdbproject.service;

import com.example.imdbproject.model.*;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class UploadToDatabaseImpl <T> {

    //String s = \N;
    static final String filePath = "C:/Users/ES/Downloads/Compressed/";

    private RatingRepository ratingRepository;
    private TitleBasicRepository titleBasicRepository;

    private  PrincipalRepository principalRepository;


    private NameBasicRepository nameBasicRepository;
    private PrimaryProfessionRepository primaryProfessionRepository;
    private GenreRepository genreRepository;

    public TitleBasicResponse reading() throws SQLException, IOException, ClassNotFoundException {

        readingFiles();

        return null;
    }

    public void readingFiles() throws IOException, SQLException, ClassNotFoundException {

        FileInputStream file = new FileInputStream("C:\\Users\\hp\\Desktop\\Desktop\\Files\\top200-IMDB-dataSet\\title.basic.1-200.tsv");
        Scanner reader = new Scanner(file);

        reader.nextLine();

        while (reader.hasNext()) {
            String[] data = reader.nextLine().split("\t");
            uploadTitleBasic(data);

        }

        file = new FileInputStream("C:\\Users\\hp\\Desktop\\Desktop\\Files\\top200-IMDB-dataSet\\title.rating.1-200.tsv");
        reader = new Scanner(file);

        reader.nextLine();

        while (reader.hasNext()) {
            String[] data = reader.nextLine().split("\t");
            uploadRating(data);

        }

        file = new FileInputStream("C:\\Users\\hp\\Desktop\\Desktop\\Files\\top200-IMDB-dataSet\\name.basic.1-200.tsv");
        reader = new Scanner(file);

        reader.nextLine();

        while (reader.hasNext()) {
            String[] data = reader.nextLine().split("\t");
            uploadNameBasic(data);

        }

        file = new FileInputStream("C:\\Users\\hp\\Desktop\\Desktop\\Files\\top200-IMDB-dataSet\\title.principals1-200.tsv");
        reader = new Scanner(file);

        reader.nextLine();

        while (reader.hasNext()) {
            String[] data = reader.nextLine().split("\t");
            uploadPrincipal(data);
        }
        file.close();
        reader.close();
    }

    public void uploadTitleBasic(String[] data) {

        Set<Genre> genreSet = new HashSet<>();
        Genre temp;
        TitleBasic buildTitleBasic = new TitleBasic();
        buildTitleBasic.setTConst(data[0]);
        buildTitleBasic.setTitleType(data[1]);
        buildTitleBasic.setPrimaryTitle(data[2]);
        if (data[3].equals("\\N")) {
            data[3] = null;
        }
        buildTitleBasic.setOriginalTitle(data[3]);
        buildTitleBasic.setAdult(Boolean.parseBoolean(data[4]));
        buildTitleBasic.setStartYear(Integer.parseInt(data[5]));
        buildTitleBasic.setEndYear(Integer.parseInt(data[5]));
        if (data[7].equals("\\N")) {
            data[7] = "0";
        }
        buildTitleBasic.setRuntime(Integer.parseInt(data[7]));
        String[] data2 = data[8].split(",");
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < data2.length; i++) {
            temp = new Genre();
            temp.setGenre(data2[i]);
            temp.setTitleBasic(data[0]);
            genreRepository.save(temp);
            genreSet.add(temp);
            stringSet.add(data2[i]);

        }
        buildTitleBasic.setAllGenres(stringSet);

        buildTitleBasic.setGenres(genreSet);
        titleBasicRepository.save(buildTitleBasic);
    }

    public void uploadRating(String[] data) {

        Rating rating = new Rating();
        Optional<TitleBasic> titleBasicOptional = titleBasicRepository.findById(data[0]);
        TitleBasic titleBasic = titleBasicOptional.get();


        rating.setTitleConst(titleBasic);
        rating.setAverageRate(Float.parseFloat(data[1]));
        rating.setVote_numbers(Integer.parseInt(data[2]));
        ratingRepository.save(rating);
        titleBasic.setRating(rating);
        titleBasicRepository.save(titleBasic);
    }


    public void uploadNameBasic(String[] data) {

        Set<PrimaryProfession> primaryProfessions = new HashSet<>();
        Set<TitleBasic> titleBasics = new HashSet<>();
        PrimaryProfession primaryProfessionTemp = new PrimaryProfession();
        TitleBasic titleBasicTemp = new TitleBasic();
        Optional<TitleBasic> titleBasicOptional;


        NameBasic nameBasic = new NameBasic();
        nameBasic.setNConst(data[0]);
        nameBasic.setPrimaryName(data[1]);
        if (data[2].equals("\\N")) {
            data[2] = "0";
        }
        nameBasic.setBirthYear(Integer.parseInt(data[2]));
        if (data[3].equals("\\N")) {
            data[3] = "0";
        }
        nameBasic.setDeathYear(Integer.parseInt(data[3]));

        nameBasicRepository.save(nameBasic);

        String[] professions = data[4].split(",");
        for (String s : professions) {
            primaryProfessionTemp.setProfession(s);
            primaryProfessionTemp.setNameBasic(nameBasic);
            primaryProfessionRepository.save(primaryProfessionTemp);
            primaryProfessions.add(primaryProfessionTemp);
            primaryProfessionTemp = new PrimaryProfession();
        }

        nameBasic.setPrimaryProfessions(primaryProfessions);

        String[] titleConsts = data[5].split(",");

        for (String titleBasic : titleConsts) {
            titleBasicOptional = titleBasicRepository.findById(titleBasic);
            if (titleBasicOptional.isPresent()) {
                titleBasicTemp = titleBasicOptional.get();
                titleBasics.add(titleBasicTemp);
                titleBasicTemp = new TitleBasic();
            }
        }

        nameBasic.setKnownForTitles(titleBasics);
        nameBasicRepository.save(nameBasic);
    }


    public void uploadPrincipal(String[] data) {
        Principal principal = new Principal();

        Optional<TitleBasic> titleBasicOptional;
        TitleBasic titleBasic;
        Optional<NameBasic> nameBasicOptional;
        NameBasic nameBasic;

        titleBasicOptional = titleBasicRepository.findById(data[0]);
        nameBasicOptional = nameBasicRepository.findById(data[2]);

        if (titleBasicOptional.isPresent())
            principal.setTConst(titleBasicOptional.get());
        if (nameBasicOptional.isPresent())
            principal.setNConst(nameBasicOptional.get());

        principal.setOrdering(Integer.parseInt(data[1]));
        principal.setCategory(data[3]);
        principalRepository.save(principal);

    }
}