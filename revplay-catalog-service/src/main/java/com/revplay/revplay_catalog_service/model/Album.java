package com.revplay.revplay_catalog_service.model;

import com.revplay.revplay_catalog_service.Enum.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long artistId;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String coverImage;

    private LocalDate releaseDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Album() {}

    public Album(Long id, String name, String description, Long artistId, Genre genre, String coverImage, LocalDate releaseDate, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.artistId = artistId;
        this.genre = genre;
        this.coverImage = coverImage;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
    }

    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public static class AlbumBuilder {
        private Long id;
        private String name;
        private String description;
        private Long artistId;
        private Genre genre;
        private String coverImage;
        private LocalDate releaseDate;
        private LocalDateTime createdAt;

        public AlbumBuilder id(Long id) { this.id = id; return this; }
        public AlbumBuilder name(String name) { this.name = name; return this; }
        public AlbumBuilder description(String description) { this.description = description; return this; }
        public AlbumBuilder artistId(Long artistId) { this.artistId = artistId; return this; }
        public AlbumBuilder genre(Genre genre) { this.genre = genre; return this; }
        public AlbumBuilder coverImage(String coverImage) { this.coverImage = coverImage; return this; }
        public AlbumBuilder releaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; return this; }
        public AlbumBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Album build() {
            return new Album(id, name, description, artistId, genre, coverImage, releaseDate, createdAt);
        }
    }
}
