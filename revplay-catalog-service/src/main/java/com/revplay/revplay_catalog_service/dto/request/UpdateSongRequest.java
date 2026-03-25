package com.revplay.revplay_catalog_service.dto.request;

import com.revplay.revplay_catalog_service.Enum.Genre;

public class UpdateSongRequest {

    private String title;
    private Genre genre;
    private Long albumId;

    public UpdateSongRequest() {}

    public UpdateSongRequest(String title, Genre genre, Long albumId) {
        this.title = title;
        this.genre = genre;
        this.albumId = albumId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }
}
