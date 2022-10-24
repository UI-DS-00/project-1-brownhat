package info.classes;

class Aka{
    private String titleId;
    private int ordering;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean is_original_type;



    //========================constructors

    public Aka(String titleId, int ordering, String region, String language, String types, String attributes, boolean is_original_type) {
        this.titleId = titleId;
        this.ordering = ordering;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.is_original_type = is_original_type;
    }

    public Aka() {
    }

    //getter setter
    public String getTitleId() {
        return titleId;
    }

    public int getOrdering() {
        return ordering;
    }

    public String getRegion() {
        return region;
    }

    public String getLanguage() {
        return language;
    }

    public String getTypes() {
        return types;
    }

    public String getAttributes() {
        return attributes;
    }

    public boolean isIs_original_type() {
        return is_original_type;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public void setIs_original_type(boolean is_original_type) {
        this.is_original_type = is_original_type;
    }
}