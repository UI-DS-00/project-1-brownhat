package info.classes;

class Episode{

    private String tConst;
    private String parentConst;
    private int seasonNumber;
    private int episodeNumber;

    //constructors============================================

    public Episode(String tConst, String parentConst, int seasonNumber, int episodeNumber) {
        this.tConst = tConst;
        this.parentConst = parentConst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public Episode() {

    }

    //getter setter ==============================================


    public String gettConst() {
        return tConst;
    }

    public void settConst(String tConst) {
        this.tConst = tConst;
    }

    public String getParentConst() {
        return parentConst;
    }

    public void setParentConst(String parentConst) {
        this.parentConst = parentConst;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}