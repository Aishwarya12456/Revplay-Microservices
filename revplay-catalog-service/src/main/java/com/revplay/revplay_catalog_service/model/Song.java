package com.revplay.revplay_catalog_service.model;

import com.revplay.revplay_catalog_service.Enum.Genre;
import com.revplay.revplay_catalog_service.Enum.Visibility;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long artistId;

    private Long albumId;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int duration;

    private String audioUrl;

    private String coverImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility = Visibility.PUBLIC;

    private LocalDate releaseDate;

    private long playCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Song() {}

    public Song(Long id, String title, Long artistId, Long albumId, Genre genre, int duration, String audioUrl, String coverImage, Visibility visibility, LocalDate releaseDate, long playCount, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genre = genre;
        this.duration = duration;
        this.audioUrl = audioUrl;
        this.coverImage = coverImage;
        this.visibility = visibility;
        this.releaseDate = releaseDate;
        this.playCount = playCount;
        this.createdAt = createdAt;
    }

    public static SongBuilder builder() {
        return new SongBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public long getPlayCount() { return playCount; }
    public void setPlayCount(long playCount) { this.playCount = playCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public static class SongBuilder {
        private Long id;
        private String title;
        private Long artistId;
        private Long albumId;
        private Genre genre;
        private int duration;
        private String audioUrl;
        private String coverImage;
        private Visibility visibility = Visibility.PUBLIC;
        private LocalDate releaseDate;
        private long playCount;
        private LocalDateTime createdAt;

        public SongBuilder id(Long id) { this.id = id; return this; }
        public SongBuilder title(String title) { this.title = title; return this; }
        public SongBuilder artistId(Long artistId) { this.artistId = artistId; return this; }
        public SongBuilder albumId(Long albumId) { this.albumId = albumId; return this; }
        public SongBuilder genre(Genre genre) { this.genre = genre; return this; }
        public SongBuilder duration(int duration) { this.duration = duration; return this; }
        public SongBuilder audioUrl(String audioUrl) { this.audioUrl = audioUrl; return this; }
        public SongBuilder coverImage(String coverImage) { this.coverImage = coverImage; return this; }
        public SongBuilder visibility(Visibility visibility) { this.visibility = visibility; return this; }
        public SongBuilder releaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; return this; }
        public SongBuilder playCount(long playCount) { this.playCount = playCount; return this; }
        public SongBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Song build() {
            return new Song(id, title, artistId, albumId, genre, duration, audioUrl, coverImage, visibility, releaseDate, playCount, createdAt);
        }
    }
}
