package com.revplay.revplay_analytics_service.dto.response;

public class ArtistSummaryResponse {

    private Long totalPlays;
    private Long totalListeners;
    private Long totalSongs;
    private Long totalAlbums;
    private Long totalFavorites;

    public ArtistSummaryResponse() {}

    public ArtistSummaryResponse(Long totalPlays, Long totalListeners, Long totalSongs, Long totalAlbums, Long totalFavorites) {
        this.totalPlays = totalPlays;
        this.totalListeners = totalListeners;
        this.totalSongs = totalSongs;
        this.totalAlbums = totalAlbums;
        this.totalFavorites = totalFavorites;
    }

    public static ArtistSummaryResponseBuilder builder() {
        return new ArtistSummaryResponseBuilder();
    }

    public static class ArtistSummaryResponseBuilder {
        private Long totalPlays;
        private Long totalListeners;
        private Long totalSongs;
        private Long totalAlbums;
        private Long totalFavorites;

        public ArtistSummaryResponseBuilder totalPlays(Long totalPlays) { this.totalPlays = totalPlays; return this; }
        public ArtistSummaryResponseBuilder totalListeners(Long totalListeners) { this.totalListeners = totalListeners; return this; }
        public ArtistSummaryResponseBuilder totalSongs(Long totalSongs) { this.totalSongs = totalSongs; return this; }
        public ArtistSummaryResponseBuilder totalAlbums(Long totalAlbums) { this.totalAlbums = totalAlbums; return this; }
        public ArtistSummaryResponseBuilder totalFavorites(Long totalFavorites) { this.totalFavorites = totalFavorites; return this; }

        public ArtistSummaryResponse build() {
            return new ArtistSummaryResponse(totalPlays, totalListeners, totalSongs, totalAlbums, totalFavorites);
        }
    }

    public Long getTotalPlays() { return totalPlays; }
    public void setTotalPlays(Long totalPlays) { this.totalPlays = totalPlays; }
    public Long getTotalListeners() { return totalListeners; }
    public void setTotalListeners(Long totalListeners) { this.totalListeners = totalListeners; }
    public Long getTotalSongs() { return totalSongs; }
    public void setTotalSongs(Long totalSongs) { this.totalSongs = totalSongs; }
    public Long getTotalAlbums() { return totalAlbums; }
    public void setTotalAlbums(Long totalAlbums) { this.totalAlbums = totalAlbums; }
    public Long getTotalFavorites() { return totalFavorites; }
    public void setTotalFavorites(Long totalFavorites) { this.totalFavorites = totalFavorites; }
}
