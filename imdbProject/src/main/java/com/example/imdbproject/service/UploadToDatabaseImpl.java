package com.example.imdbproject.service;

import com.example.imdbproject.model.Genre;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.repository.GenreRepository;
import com.example.imdbproject.repository.RatingRepository;
import com.example.imdbproject.repository.TitleBasicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class UploadToDatabaseImpl {


    //String s = \N;
    static final String filePath = "C:/Users/ES/Downloads/Compressed/";

    private RatingRepository ratingRepository;
    private TitleBasicRepository titleBasicRepository;

    private GenreRepository genreRepository;
    public TitleBasicResponse reading() throws SQLException, IOException, ClassNotFoundException {
        readingFiles("C:\\Users\\hp\\Desktop\\Desktop\\Files\\titleBasic.tsv");

//        reading_Files("title.akas.tsv");
//
//        reading_Files("title.basics.tsv");
//
//        reading_Files("title.crew.tsv");
//
//        reading_Files("title.episode.tsv");
//
//        reading_Files("title.principals.tsv");
//
//    1    reading_Files("title.ratings.tsv");

        return null;
    }

    public void readingFiles(String fileName) throws IOException, SQLException, ClassNotFoundException {

        FileInputStream file = new FileInputStream(fileName);
        Scanner reader = new Scanner(file);

        reader.nextLine();

        while (reader.hasNext()) {
                String[] data = reader.nextLine().split("\t");
            uploadTitleBasic(data);

        }

        file.close();
        reader.close();
    }


    public void uploadTitleBasic(String[] data){

        Set<Genre> genreSet = new HashSet<>();
        Genre temp;
        TitleBasic buildTitleBasic = new TitleBasic();
        buildTitleBasic.setTConst(data[0]);
        buildTitleBasic.setTitleType(data[1]);
        buildTitleBasic.setPrimaryTitle(data[2]);
        if (data[3].equals("\\N"))
            data[3] = null;
        buildTitleBasic.setOriginalTitle(data[3]);
        buildTitleBasic.setAdult(Boolean.parseBoolean(data[4]));
        buildTitleBasic.setStartYear(Integer.parseInt(data[5]));
        if (data[6].equals("\\N"))
            data[6] = "0";
        buildTitleBasic.setEndYear(Integer.parseInt(data[6]));
        if (data[7].equals("\\N"))
            data[7] = "0";
        buildTitleBasic.setRuntime(Integer.parseInt(data[7]));
        String[] data2 = data[8].split(",");
        for(int i =0;i<data2.length;i++){
            temp=new Genre();
            temp.setGenre(data2[i]);
            temp.setTitleBasic(data[0]);
            genreRepository.save(temp);
            genreSet.add(temp);
        }
        buildTitleBasic.setGenres(genreSet);
        System.out.println(buildTitleBasic.getTConst());
        titleBasicRepository.save(buildTitleBasic);

    }

    public void uploadToRating(){
    }
}


