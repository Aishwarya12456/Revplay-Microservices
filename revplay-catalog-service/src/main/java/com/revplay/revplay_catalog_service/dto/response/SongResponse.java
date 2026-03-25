package com.revplay.revplay_catalog_service.dto.response;


public class SongResponse {

    private Long id;
    private Long artistId;
    private String title;

    public SongResponse() {}

    public SongResponse(Long id, Long artistId, String title) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
