package info.classes;

import java.util.ArrayList;

class Crew{

    private String ID;
    private ArrayList<String> directors;
    private ArrayList <String> writers;


    //constructors==================


    public Crew(String ID, ArrayList<String> directors, ArrayList<String> writers) {
        this.ID = ID;
        this.directors = directors;
        this.writers = writers;
    }

    public Crew() {

    }

    //getter setter =======================


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }
}