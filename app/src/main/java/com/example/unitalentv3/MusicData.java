package com.example.unitalentv3;

public class MusicData {
    private  String musicTitle;
    private  String musicArtist;
    private Integer musicImage;

    public MusicData(String musicTitle, String musicArtist, Integer musicImage) {
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicImage = musicImage;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public Integer getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(Integer musicImage) {
        this.musicImage = musicImage;
    }
}
