package com.example.imdbproject.config;

import com.example.imdbproject.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public class UploadingFiles {

    private static String url="/";

    public void reading() throws SQLException, IOException, ClassNotFoundException {

        new SendingIntoTables<TitleBasic>("title.basics.tsv" , new TitleBasic());

        new SendingIntoTables<NameBasic>("name.basics.tsv" , new NameBasic());

        new SendingIntoTables<Aka>("title.akas.tsv" , new Aka());

        new SendingIntoTables<Crew>("title.crew.tsv" , new Crew());

        new SendingIntoTables<Episode>("title.episode.tsv" , new Episode());

        new SendingIntoTables<Principal>("title.principals.tsv" ,  new Principal());

        new SendingIntoTables<Rating>("title.ratings.tsv" ,  new Rating());

    }


    class SendingIntoTables <MODEL>{

        public SendingIntoTables(String filePath , MODEL row) throws IOException {
            readingFiles(filePath , row);
        }

        public void readingFiles(String filePath , MODEL row) throws IOException {

            FileInputStream fileInputStream= new FileInputStream(url+filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);




        }
    }
}
