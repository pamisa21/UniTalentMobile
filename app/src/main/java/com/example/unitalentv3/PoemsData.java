package com.example.unitalentv3;

public class PoemsData {
    private String poemTitle;
    private String poemAuthor;
    private String poemDescription;

    public PoemsData(String poemTitle, String poemAuthor, String poemDescription) {
        this.poemTitle = poemTitle;
        this.poemAuthor = poemAuthor;
        this.poemDescription = poemDescription;
    }

    public String getPoemTitle() {
        return poemTitle;
    }

    public void setPoemTitle(String poemTitle) {
        this.poemTitle = poemTitle;
    }

    public String getPoemAuthor() {
        return poemAuthor;
    }

    public void setPoemAuthor(String poemAuthor) {
        this.poemAuthor = poemAuthor;
    }

    public String getPoemDescription() {
        return poemDescription;
    }

    public void setPoemDescription(String poemDescription) {
        this.poemDescription = poemDescription;
    }
}
