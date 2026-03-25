package com.revplay.revplay_analytics_service.dto.response;

public class TopSongProjection {

    private Long songId;
    private Long playCount;

    public TopSongProjection() {}

    public TopSongProjection(Long songId, Long playCount) {
        this.songId = songId;
        this.playCount = playCount;
    }

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }
}