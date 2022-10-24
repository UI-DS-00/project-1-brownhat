package info.classes;

import java.util.ArrayList;

class TitleBasic {

    private String ID;
    private String title_type;
    private String primary_title;
    private String original_title;
    private boolean is_adult;
    private int start_year;
    private int end_year;
    private int runtime;

    private ArrayList<String> genre;

    //constructors================================


    public TitleBasic(String ID, String title_type, String primary_title, String original_title,
                      boolean is_adult, int start_year, int end_year, int runtime, ArrayList<String> genre) {
        this.ID = ID;
        this.title_type = title_type;
        this.primary_title = primary_title;
        this.original_title = original_title;
        this.is_adult = is_adult;
        this.start_year = start_year;
        this.end_year = end_year;
        this.runtime = runtime;
        this.genre = genre;
    }

    public TitleBasic() {

    }

    //getter setter========================


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle_type() {
        return title_type;
    }

    public void setTitle_type(String title_type) {
        this.title_type = title_type;
    }

    public String getPrimary_title() {
        return primary_title;
    }

    public void setPrimary_title(String primary_title) {
        this.primary_title = primary_title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public boolean isIs_adult() {
        return is_adult;
    }

    public void setIs_adult(boolean is_adult) {
        this.is_adult = is_adult;
    }

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}