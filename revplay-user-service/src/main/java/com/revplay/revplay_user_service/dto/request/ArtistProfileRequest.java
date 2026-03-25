package com.revplay.revplay_user_service.dto.request;

import com.revplay.revplay_user_service.Enum.Genre;

public class ArtistProfileRequest {
    private String artistName;
    private Genre genre;
    private String bio;
    private String instagram;
    private String twitter;
    private String youtube;
    private String spotify;
    private String website;

    public ArtistProfileRequest() {}

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }
    public String getYoutube() { return youtube; }
    public void setYoutube(String youtube) { this.youtube = youtube; }
    public String getSpotify() { return spotify; }
    public void setSpotify(String spotify) { this.spotify = spotify; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}
