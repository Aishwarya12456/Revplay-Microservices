package com.revplay.revplay_playback_service.dto.request;

public class TrackPlayRequest {

    private Long songId;
    private Long artistId;
    private Long userId;

    public TrackPlayRequest() {}

    public TrackPlayRequest(Long songId, Long artistId, Long userId) {
        this.songId = songId;
        this.artistId = artistId;
        this.userId = userId;
    }

    public static TrackPlayRequestBuilder builder() {
        return new TrackPlayRequestBuilder();
    }

    public static class TrackPlayRequestBuilder {
        private Long songId;
        private Long artistId;
        private Long userId;

        public TrackPlayRequestBuilder songId(Long songId) { this.songId = songId; return this; }
        public TrackPlayRequestBuilder artistId(Long artistId) { this.artistId = artistId; return this; }
        public TrackPlayRequestBuilder userId(Long userId) { this.userId = userId; return this; }

        public TrackPlayRequest build() {
            return new TrackPlayRequest(songId, artistId, userId);
        }
    }

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}