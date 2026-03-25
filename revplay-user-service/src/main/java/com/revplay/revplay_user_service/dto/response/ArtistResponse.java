package com.revplay.revplay_user_service.dto.response;

import com.revplay.revplay_user_service.Enum.Genre;

public class ArtistResponse {


    public ArtistResponse() {}

    public ArtistResponse(Long id, String artistName, Genre genre, String bio, String bannerImage, String profilePicture, String instagram, String spotify, String twitter, String youtube, String website) {
        this.id = id;
        this.artistName = artistName;
        this.genre = genre;
        this.bio = bio;
        this.bannerImage = bannerImage;
        this.profilePicture = profilePicture;
        this.instagram = instagram;
        this.spotify = spotify;
        this.twitter = twitter;
        this.youtube = youtube;
        this.website = website;
    }

    public static ArtistResponseBuilder builder() {
        return new ArtistResponseBuilder();
    }

    public static class ArtistResponseBuilder {
        private Long id;
        private String artistName;
        private Genre genre;
        private String bio;
        private String bannerImage;
        private String profilePicture;
        private String instagram;
        private String spotify;
        private String twitter;
        private String youtube;
        private String website;

        public ArtistResponseBuilder id(Long id) { this.id = id; return this; }
        public ArtistResponseBuilder artistName(String artistName) { this.artistName = artistName; return this; }
        public ArtistResponseBuilder genre(Genre genre) { this.genre = genre; return this; }
        public ArtistResponseBuilder bio(String bio) { this.bio = bio; return this; }
        public ArtistResponseBuilder bannerImage(String bannerImage) { this.bannerImage = bannerImage; return this; }
        public ArtistResponseBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }
        public ArtistResponseBuilder instagram(String instagram) { this.instagram = instagram; return this; }
        public ArtistResponseBuilder spotify(String spotify) { this.spotify = spotify; return this; }
        public ArtistResponseBuilder twitter(String twitter) { this.twitter = twitter; return this; }
        public ArtistResponseBuilder youtube(String youtube) { this.youtube = youtube; return this; }
        public ArtistResponseBuilder website(String website) { this.website = website; return this; }

        public ArtistResponse build() {
            return new ArtistResponse(id, artistName, genre, bio, bannerImage, profilePicture, instagram, spotify, twitter, youtube, website);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getBannerImage() { return bannerImage; }
    public void setBannerImage(String bannerImage) { this.bannerImage = bannerImage; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public String getSpotify() { return spotify; }
    public void setSpotify(String spotify) { this.spotify = spotify; }
    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }
    public String getYoutube() { return youtube; }
    public void setYoutube(String youtube) { this.youtube = youtube; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }


    private Long id;
    private String artistName;
    private Genre genre;
    private String bio;
    private String bannerImage;
    private String profilePicture;
    private String instagram;
    private String spotify;
    private String twitter;
    private String youtube;
    private String website;
}
