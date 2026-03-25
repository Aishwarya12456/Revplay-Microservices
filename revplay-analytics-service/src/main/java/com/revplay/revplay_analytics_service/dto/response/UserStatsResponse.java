package com.revplay.revplay_analytics_service.dto.response;

public class UserStatsResponse {

    private Long favoriteSongs;
    private Long totalPlayCount;
    private Long totalPlaylists;

    public UserStatsResponse() {}

    public UserStatsResponse(Long favoriteSongs, Long totalPlayCount, Long totalPlaylists) {
        this.favoriteSongs = favoriteSongs;
        this.totalPlayCount = totalPlayCount;
        this.totalPlaylists = totalPlaylists;
    }

    public static UserStatsResponseBuilder builder() {
        return new UserStatsResponseBuilder();
    }

    public static class UserStatsResponseBuilder {
        private Long favoriteSongs;
        private Long totalPlayCount;
        private Long totalPlaylists;

        public UserStatsResponseBuilder favoriteSongs(Long favoriteSongs) { this.favoriteSongs = favoriteSongs; return this; }
        public UserStatsResponseBuilder totalPlayCount(Long totalPlayCount) { this.totalPlayCount = totalPlayCount; return this; }
        public UserStatsResponseBuilder totalPlaylists(Long totalPlaylists) { this.totalPlaylists = totalPlaylists; return this; }

        public UserStatsResponse build() {
            return new UserStatsResponse(favoriteSongs, totalPlayCount, totalPlaylists);
        }
    }

    public Long getFavoriteSongs() { return favoriteSongs; }
    public void setFavoriteSongs(Long favoriteSongs) { this.favoriteSongs = favoriteSongs; }
    public Long getTotalPlayCount() { return totalPlayCount; }
    public void setTotalPlayCount(Long totalPlayCount) { this.totalPlayCount = totalPlayCount; }
    public Long getTotalPlaylists() { return totalPlaylists; }
    public void setTotalPlaylists(Long totalPlaylists) { this.totalPlaylists = totalPlaylists; }
}
