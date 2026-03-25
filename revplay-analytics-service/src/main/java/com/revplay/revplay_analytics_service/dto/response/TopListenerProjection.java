package com.revplay.revplay_analytics_service.dto.response;

public class TopListenerProjection {

    private Long userId;
    private Long playCount;

    public TopListenerProjection() {}

    public TopListenerProjection(Long userId, Long playCount) {
        this.userId = userId;
        this.playCount = playCount;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }
}
