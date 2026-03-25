package com.revplay.revplay_analytics_service.dto.response;

public class SongResponse {

    private Long id;
    private String title;
    private Long artistId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
}