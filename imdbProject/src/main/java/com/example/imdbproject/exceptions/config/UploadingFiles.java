package com.example.imdbproject.exceptions.config;

import com.example.imdbproject.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public class UploadingFiles {

    private static String url="/";

    public void reading() throws SQLException, IOException, ClassNotFoundException {

        new SendingIntoTables<TitleBasic>("name.basic.1-200.tsv" , new TitleBasic());

        new SendingIntoTables<NameBasic>("title.basic.1-200.tsv" , new NameBasic());

        new SendingIntoTables<Principal>("title.principals1-200.tsv" ,  new Principal());

        new SendingIntoTables<Rating>("title.rating.1-200.tsv" ,  new Rating());

    }


    class SendingIntoTables <MODEL>{

        public SendingIntoTables(String filePath , MODEL row) throws IOException {
            readingFiles(filePath , row);
        }

        public void readingFiles(String filePath , MODEL row) throws IOException {

            FileInputStream fileInputStream= new FileInputStream(url+filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            MODEL m = (MODEL)objectInputStream;



        }
    }
}
