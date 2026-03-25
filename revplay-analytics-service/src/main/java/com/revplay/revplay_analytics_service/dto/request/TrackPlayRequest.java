package com.revplay.revplay_analytics_service.dto.request;

public class TrackPlayRequest {

    private Long songId;
    private Long artistId;
    private Long userId;

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}