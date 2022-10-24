package info.classes;

class Rating {

    private String tConst;
    private float averageRate;
    private int vote_numbers;


    //constructors========================


    public Rating(String tConst, float averageRate, int vote_numbers) {
        this.tConst = tConst;
        this.averageRate = averageRate;
        this.vote_numbers = vote_numbers;
    }

    public Rating() {

    }

    //getter setter ===================================


    public String gettConst() {
        return tConst;
    }

    public void settConst(String tConst) {
        this.tConst = tConst;
    }

    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    public int getVote_numbers() {
        return vote_numbers;
    }

    public void setVote_numbers(int vote_numbers) {
        this.vote_numbers = vote_numbers;
    }
}