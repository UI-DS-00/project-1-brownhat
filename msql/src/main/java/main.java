import java.sql.*;

public class main {
    public static void main(String[] args) throws Exception{

        new SQLWriter().akaWriter("2",2,"2","2","2","2",1);
        new SQLWriter().nameBasicsWriter("1",1,"1",1,1,"1","1");
        new SQLWriter().titleBasicWriter("1","1","1","1",1,1,2,1,"1");
        new SQLWriter().principalWriter("1",1,"1","1","1","1");
        new SQLWriter().crewWriter("1","1","1");
        new SQLWriter().episodeWriter("1","1",1,1);
        new SQLWriter().ratingWriter("1",12.5,125);

    }
}
