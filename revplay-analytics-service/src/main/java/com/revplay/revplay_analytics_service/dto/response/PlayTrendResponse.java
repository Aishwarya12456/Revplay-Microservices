package com.revplay.revplay_analytics_service.dto.response;

public class PlayTrendResponse {

    private String period;
    private Long playCount;

    public PlayTrendResponse() {}

    public PlayTrendResponse(String period, Long playCount) {
        this.period = period;
        this.playCount = playCount;
    }

    public static PlayTrendResponseBuilder builder() {
        return new PlayTrendResponseBuilder();
    }

    public static class PlayTrendResponseBuilder {
        private String period;
        private Long playCount;

        public PlayTrendResponseBuilder period(String period) { this.period = period; return this; }
        public PlayTrendResponseBuilder playCount(Long playCount) { this.playCount = playCount; return this; }

        public PlayTrendResponse build() {
            return new PlayTrendResponse(period, playCount);
        }
    }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }
}
