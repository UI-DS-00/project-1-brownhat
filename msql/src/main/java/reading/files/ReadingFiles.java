package reading.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import sql.SQLWriter;

public class ReadingFiles
{
    static final String filePath = "C:/Users/ES/Downloads/Compressed/";


    public static void reading() throws SQLException, IOException, ClassNotFoundException {
        reading_Files("name.basics.tsv");

        reading_Files("title.akas.tsv");

        reading_Files("title.basics.tsv");

        reading_Files("title.crew.tsv");

        reading_Files("title.episode.tsv");

        reading_Files("title.principals.tsv");

        reading_Files("title.ratings.tsv");

    }

    public static void reading_Files( String fileName) throws IOException, SQLException, ClassNotFoundException {

        FileInputStream file=new FileInputStream(filePath+fileName);
        Scanner reader = new Scanner(file);

        reader.next();

        while (reader.hasNext())
        {
            String [] data= reader.next().split("\\s");
            //findTable(data , fileName);
        }

        file.close();
        reader.close();
    }



    public static void findTable(String [] data , String name) throws IOException, SQLException, ClassNotFoundException {

        if (name.equals("name.basics.tsv"))
            SQLWriter.nameBasicsWriter(data[0], Long.parseLong(data[2]), data[3],Integer.parseInt(data[4])
                    ,Integer.parseInt(data[5]) , data[6] , data[7]);

        else if (name.equals("title.akas.tsv"))
            SQLWriter.akaWriter(data[0], Integer.parseInt(data[2]), data[3],data[4],
                    data[5] , data[6] ,Integer.parseInt( data[7] ));
        else if (name.equals("title.basics.tsv"))
            SQLWriter.titleBasicWriter(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5])
            , Integer.parseInt(data[6] ), Integer.parseInt(data[7] ), data[8] );
        else if (name.equals("title.crew.tsv"))
            SQLWriter.crewWriter(data[0],data[1],data[2]);
        else if (name.equals("title.episode.tsv"))
            SQLWriter.episodeWriter(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data [3]));
        else if (name.equals("title.principals.tsv"))
            SQLWriter.principalWriter(data[0],Integer.parseInt(data[1]),data[2],data[3],data[4],data[5] );
        else if (name.equals("title.ratings.tsv"))
        SQLWriter.ratingWriter(data[0],Double.parseDouble(data[1]), Integer.parseInt(data[2]));


    }

}
