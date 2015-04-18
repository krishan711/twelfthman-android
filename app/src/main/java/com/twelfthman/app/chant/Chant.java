package com.twelfthman.app.chant;

/**
 * Created by dhilipb on 18/04/2015.
 */
public class Chant {

    private String team;
    private String title;
    private String lyrics;

    public Chant(String team, String title, String lyrics)
    {
        this.team = team;
        this.title = title;
        this.lyrics = lyrics;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
