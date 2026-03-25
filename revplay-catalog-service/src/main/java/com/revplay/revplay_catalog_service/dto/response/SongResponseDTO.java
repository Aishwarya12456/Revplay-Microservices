package com.revplay.revplay_catalog_service.dto.response;

import com.revplay.revplay_catalog_service.Enum.Genre;
import com.revplay.revplay_catalog_service.Enum.Visibility;

public class SongResponseDTO {

    private Long id;
    private String title;
    private Genre genre;
    private int duration;
    private String audioUrl;
    private String coverArtUrl;
    private Long artistId;
    private String artistName;
    private Long albumId;
    private String albumName;
    private Visibility visibility;

    public SongResponseDTO() {}

    public SongResponseDTO(Long id, String title, Genre genre, int duration, String audioUrl, String coverArtUrl, Long artistId, String artistName, Long albumId, String albumName, Visibility visibility) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.audioUrl = audioUrl;
        this.coverArtUrl = coverArtUrl;
        this.artistId = artistId;
        this.artistName = artistName;
        this.albumId = albumId;
        this.albumName = albumName;
        this.visibility = visibility;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }

    public String getCoverArtUrl() { return coverArtUrl; }
    public void setCoverArtUrl(String coverArtUrl) { this.coverArtUrl = coverArtUrl; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }

    public String getAlbumName() { return albumName; }
    public void setAlbumName(String albumName) { this.albumName = albumName; }

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
}