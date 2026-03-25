package com.revplay.revplay_user_service.dto.response;

public class ArtistDTO {
    private Long id;
    private String artistName;
    private String bio;
    private String profilePicture;
    private String genre;

    public ArtistDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public static ArtistDTOBuilder builder() {
        return new ArtistDTOBuilder();
    }

    public static class ArtistDTOBuilder {
        private Long id;
        private String artistName;
        private String bio;
        private String profilePicture;
        private String genre;

        public ArtistDTOBuilder id(Long id) { this.id = id; return this; }
        public ArtistDTOBuilder artistName(String artistName) { this.artistName = artistName; return this; }
        public ArtistDTOBuilder bio(String bio) { this.bio = bio; return this; }
        public ArtistDTOBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }
        public ArtistDTOBuilder genre(String genre) { this.genre = genre; return this; }

        public ArtistDTO build() {
            ArtistDTO dto = new ArtistDTO();
            dto.setId(id);
            dto.setArtistName(artistName);
            dto.setBio(bio);
            dto.setProfilePicture(profilePicture);
            dto.setGenre(genre);
            return dto;
        }
    }
}
