package info.classes;

class Principal{

    private String tConst;
    private int ordering;
    private String nConst;
    private String category;
    private String job;
    private String characters;

    //constructors============================


    public Principal(String tConst, int ordering, String nConst, String category, String job, String characters) {
        this.tConst = tConst;
        this.ordering = ordering;
        this.nConst = nConst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public Principal() {
    }

    //getter setter

    public String gettConst() {
        return tConst;
    }

    public void settConst(String tConst) {
        this.tConst = tConst;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getnConst() {
        return nConst;
    }

    public void setnConst(String nConst) {
        this.nConst = nConst;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}