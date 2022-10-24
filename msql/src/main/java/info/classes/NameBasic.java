package info.classes;

import java.util.ArrayList;

class NameBasic{

    private String nConst;
    private long personId;
    private String primaryName;
    private int birth_year;
    private int death_year;

    private ArrayList<String> primary_professions;
    private ArrayList <String> KnownForTitles;

    //constructors ========================================


    public NameBasic(String nConst, long personId, String primaryName, int birth_year, int death_year, ArrayList<String> primary_professions, ArrayList<String> knownForTitles) {
        this.nConst = nConst;
        this.personId = personId;
        this.primaryName = primaryName;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.primary_professions = primary_professions;
        KnownForTitles = knownForTitles;
    }

    public NameBasic() {
    }

    //setter getter ==========================


    public String getnConst() {
        return nConst;
    }

    public void setnConst(String nConst) {
        this.nConst = nConst;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public ArrayList<String> getPrimary_professions() {
        return primary_professions;
    }

    public void setPrimary_professions(ArrayList<String> primary_professions) {
        this.primary_professions = primary_professions;
    }

    public ArrayList<String> getKnownForTitles() {
        return KnownForTitles;
    }

    public void setKnownForTitles(ArrayList<String> knownForTitles) {
        KnownForTitles = knownForTitles;
    }
}