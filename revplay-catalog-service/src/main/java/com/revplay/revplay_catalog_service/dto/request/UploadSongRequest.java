package com.revplay.revplay_catalog_service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revplay.revplay_catalog_service.Enum.Genre;

import java.time.LocalDate;

public class UploadSongRequest {

    private String title;
    private Long albumId;
    private Genre genre;
    private int duration;
    private String releaseDate;

    public UploadSongRequest() {}

    public UploadSongRequest(String title, Long albumId, Genre genre, int duration, String releaseDate) {
        this.title = title;
        this.albumId = albumId;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}
