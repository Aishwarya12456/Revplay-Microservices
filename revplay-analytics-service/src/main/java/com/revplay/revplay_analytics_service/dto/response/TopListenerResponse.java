package com.revplay.revplay_analytics_service.dto.response;

public class TopListenerResponse {

    private Long userId;
    private String username;
    private Long playCount;

    public TopListenerResponse() {}

    public TopListenerResponse(Long userId, String username, Long playCount) {
        this.userId = userId;
        this.username = username;
        this.playCount = playCount;
    }

    public static TopListenerResponseBuilder builder() {
        return new TopListenerResponseBuilder();
    }

    public static class TopListenerResponseBuilder {
        private Long userId;
        private String username;
        private Long playCount;

        public TopListenerResponseBuilder userId(Long userId) { this.userId = userId; return this; }
        public TopListenerResponseBuilder username(String username) { this.username = username; return this; }
        public TopListenerResponseBuilder playCount(Long playCount) { this.playCount = playCount; return this; }

        public TopListenerResponse build() {
            return new TopListenerResponse(userId, username, playCount);
        }
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }
}