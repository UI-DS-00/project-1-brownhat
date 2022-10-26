package info.classes;

import java.util.ArrayList;

public class AllUser
{
    private String username;
    private String password;

    private ArrayList <Comment> comments;
    private ArrayList <String> watchList;

    private ArrayList <ArrayList<String>> favoriteLists;


    //constructors=======================================================
    public AllUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.comments = new ArrayList<>();
        this.watchList = new ArrayList<>();
        this.favoriteLists = new ArrayList<>();
    }

    public AllUser() {
    }

    //getter setter =========================================================


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getWatchList() {
        return watchList;
    }

    public void setWatchList(ArrayList<String> watchList) {
        this.watchList = watchList;
    }

    public ArrayList<ArrayList<String>> getFavoriteLists() {
        return favoriteLists;
    }

    public void setFavoriteLists(ArrayList<ArrayList<String>> favoriteLists) {
        this.favoriteLists = favoriteLists;
    }
}
