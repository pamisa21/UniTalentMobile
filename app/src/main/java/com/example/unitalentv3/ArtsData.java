package com.example.unitalentv3;

public class ArtsData {
    private String artTitle;
    private String artistName;
    private Integer artImage;

    public ArtsData(String artTitle, String artistName, Integer artImage) {
        this.artTitle = artTitle;
        this.artistName = artistName;
        this.artImage = artImage;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getArtImage() {
        return artImage;
    }

    public void setArtImage(Integer artImage) {
        this.artImage = artImage;
    }
}
