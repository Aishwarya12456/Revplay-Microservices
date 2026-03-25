package com.revplay.revplay_playback_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "listening_history",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "songId"})
        }
)
public class ListeningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;

    private LocalDateTime playedAt;

    public ListeningHistory() {}

    public ListeningHistory(Long id, Long userId, Long songId, LocalDateTime playedAt) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.playedAt = playedAt;
    }

    public static ListeningHistoryBuilder builder() {
        return new ListeningHistoryBuilder();
    }

    public static class ListeningHistoryBuilder {
        private Long id;
        private Long userId;
        private Long songId;
        private LocalDateTime playedAt;

        public ListeningHistoryBuilder id(Long id) { this.id = id; return this; }
        public ListeningHistoryBuilder userId(Long userId) { this.userId = userId; return this; }
        public ListeningHistoryBuilder songId(Long songId) { this.songId = songId; return this; }
        public ListeningHistoryBuilder playedAt(LocalDateTime playedAt) { this.playedAt = playedAt; return this; }

        public ListeningHistory build() {
            return new ListeningHistory(id, userId, songId, playedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    @PrePersist
    public void onCreate() {
        playedAt = LocalDateTime.now();
    }
}