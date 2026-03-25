package com.revplay.revplay_catalog_service.dto.response;


public class ArtistDTO {

    private Long id;
    private String artistName;
    private String bio;
    private String genre;
    private String profilePicture;

    public ArtistDTO() {}

    public ArtistDTO(Long id, String artistName, String bio, String genre, String profilePicture) {
        this.id = id;
        this.artistName = artistName;
        this.bio = bio;
        this.genre = genre;
        this.profilePicture = profilePicture;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
}
