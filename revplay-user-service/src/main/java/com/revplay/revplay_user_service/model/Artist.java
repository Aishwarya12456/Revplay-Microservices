package com.revplay.revplay_user_service.model;

import com.revplay.revplay_user_service.Enum.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    public Artist() {}

    public Artist(Long id, String artistName, String bio, Genre genre, String profilePicture, String bannerImage, String instagram, String twitter, String youtube, String spotify, String website, User user) {
        this.id = id;
        this.artistName = artistName;
        this.bio = bio;
        this.genre = genre;
        this.profilePicture = profilePicture;
        this.bannerImage = bannerImage;
        this.instagram = instagram;
        this.twitter = twitter;
        this.youtube = youtube;
        this.spotify = spotify;
        this.website = website;
        this.user = user;
    }

    public static ArtistBuilder builder() {
        return new ArtistBuilder();
    }

    public static class ArtistBuilder {
        private Long id;
        private String artistName;
        private String bio;
        private Genre genre;
        private String profilePicture;
        private String bannerImage;
        private String instagram;
        private String twitter;
        private String youtube;
        private String spotify;
        private String website;
        private User user;

        public ArtistBuilder id(Long id) { this.id = id; return this; }
        public ArtistBuilder artistName(String artistName) { this.artistName = artistName; return this; }
        public ArtistBuilder bio(String bio) { this.bio = bio; return this; }
        public ArtistBuilder genre(Genre genre) { this.genre = genre; return this; }
        public ArtistBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }
        public ArtistBuilder bannerImage(String bannerImage) { this.bannerImage = bannerImage; return this; }
        public ArtistBuilder instagram(String instagram) { this.instagram = instagram; return this; }
        public ArtistBuilder twitter(String twitter) { this.twitter = twitter; return this; }
        public ArtistBuilder youtube(String youtube) { this.youtube = youtube; return this; }
        public ArtistBuilder spotify(String spotify) { this.spotify = spotify; return this; }
        public ArtistBuilder website(String website) { this.website = website; return this; }
        public ArtistBuilder user(User user) { this.user = user; return this; }

        public Artist build() {
            return new Artist(id, artistName, bio, genre, profilePicture, bannerImage, instagram, twitter, youtube, spotify, website, user);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public String getBannerImage() { return bannerImage; }
    public void setBannerImage(String bannerImage) { this.bannerImage = bannerImage; }
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
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Artist(id=" + id + ", artistName=" + artistName + ", genre=" + genre + ")";
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name", nullable = false, length = 50)
    private String artistName;

    private String bio;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "profile_pic")
    private String profilePicture;
    private String bannerImage;

    private String instagram;
    private String twitter;
    private String youtube;
    private String spotify;
    private String website;

    @OneToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    private User user;
}
