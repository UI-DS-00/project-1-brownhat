import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLWriter {
    public void akaWriter(String titleId,int ordering,String region,String language,String types,String attributes,int isOriginal) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `aka`(`titleId`, `ordering`, `region`, `language`, `types`, `attributes`, `isOriginal`)" +
                " VALUES ('%s','%s','%s','%s','%s','%s','%s') " ,titleId,ordering,region,language,types,attributes,isOriginal);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void nameBasicsWriter(String nConst,long personID,String primaryName,int birthYear,int deathYear,String primaryProfession,String knownFor) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `name`(`nConst`, `personID`, `primaryName`, `birthYear`, `deathYear`, `primaryProfession`, `knownFor`)" +
                " VALUES ('%s','%s','%s','%s','%s','%s','%s') " ,nConst,personID,primaryName,birthYear,deathYear,primaryProfession,knownFor);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void titleBasicWriter(String titleId,String titleType,String primaryTitle,String originalTitle,int isAdult,int startYear,int endYear,int runtimeMinute,String genre) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `title`(`titleId`, `titleType`, `primaryTitle`, `originalTitle`, `isAdult`, `startYear`, `endYear`, `runtimeMinute`, `genre`)" +
                " VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s') " ,titleId,titleType,primaryTitle,originalTitle,isAdult,startYear,endYear,runtimeMinute,genre);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void episodeWriter(String tConst,String parentConst,int seasonNumber,int episodeNumber) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `episode`(`tConst`, `parentConst`, `seasonNumber`, `episodeNumber`)" +
                " VALUES ('%s','%s','%s','%s') " ,tConst,parentConst,seasonNumber,episodeNumber);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void principalWriter(String tConst,int ordering,String nConst,String category,String job,String characters) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `principal`(`tConst`, `ordering`, `nConst`, `category`, `job`, `characters`)" +
                " VALUES ('%s','%s','%s','%s','%s','%s') " ,tConst,ordering,nConst,category,job,characters);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void crewWriter(String titleID,String directors,String writers) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `crew`(`titleID`, `directors`, `writers`)" +
                " VALUES ('%s','%s','%s') " ,titleID,directors,writers);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

    public void ratingWriter(String tConst,double average,int numberOfVotes) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/dsproject";
        String uname = "root";
        String pw = "12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pw);
        System.out.println("connected");
        String sqlCom = String.format("INSERT INTO `rating`(`tConst`, `average`, `numberOfVotes`)" +
                " VALUES ('%s','%s','%s') " ,tConst,average,numberOfVotes);
        Statement s = con.prepareStatement(sqlCom);
        s.execute(sqlCom);
        con.close();
        System.out.println("finished!!!");
    }

}

