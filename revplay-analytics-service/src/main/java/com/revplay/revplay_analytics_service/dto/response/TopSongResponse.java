package com.revplay.revplay_analytics_service.dto.response;

public class TopSongResponse {

    private Long songId;
    private String title;
    private Long playCount;

    public TopSongResponse() {}

    public TopSongResponse(Long songId, String title, Long playCount) {
        this.songId = songId;
        this.title = title;
        this.playCount = playCount;
    }

    public static TopSongResponseBuilder builder() {
        return new TopSongResponseBuilder();
    }

    public static class TopSongResponseBuilder {
        private Long songId;
        private String title;
        private Long playCount;

        public TopSongResponseBuilder songId(Long songId) { this.songId = songId; return this; }
        public TopSongResponseBuilder title(String title) { this.title = title; return this; }
        public TopSongResponseBuilder playCount(Long playCount) { this.playCount = playCount; return this; }

        public TopSongResponse build() {
            return new TopSongResponse(songId, title, playCount);
        }
    }

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }
}