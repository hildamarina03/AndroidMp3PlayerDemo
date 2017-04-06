package org.mdsd2017.android.androidmp3playerdemo.models;

import org.mdsd2017.android.androidmp3playerdemo.R;

import java.io.Serializable;

/**
 * Created by hilda on 04/04/17.
 */

public class Song implements Serializable {

    private String title;
    private int resource;
    private String country;
    private int duration; // Duration in seconds
    private int image;
    private String comments;

    public Song(String title, int resource, String country, int duration, int image, String comments) {
        this.title = title;
        this.resource = resource;
        this.country = country;
        this.duration = duration;
        this.image = image;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getCountry() {
        return "From: "+country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getImage() {
        if(image == 0) return R.drawable.placeholder;
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDurationInMinutes() {
        int minutes = duration/60;
        int seconds = duration - (minutes * 60);

        String secondsString = String.valueOf(seconds);
        if(seconds < 10) secondsString = "0" + secondsString;
        String minutesString = String.valueOf(minutes);
        if(minutes < 10) minutesString = "0" + minutesString;

        return "Duration: " + minutesString + ":" + secondsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return title.equals(song.title);

    }

    @Override
    public int hashCode() {

        return title.hashCode();
    }
}
