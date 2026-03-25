package com.revplay.revplay_playback_service.dto.response;

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

    public static SongResponseBuilder builder() {
        return new SongResponseBuilder();
    }

    public static class SongResponseBuilder {
        private Long id;
        private Long artistId;
        private String title;

        public SongResponseBuilder id(Long id) { this.id = id; return this; }
        public SongResponseBuilder artistId(Long artistId) { this.artistId = artistId; return this; }
        public SongResponseBuilder title(String title) { this.title = title; return this; }

        public SongResponse build() {
            return new SongResponse(id, artistId, title);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
