package com.revplay.revplay_analytics_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "song_plays")
public class SongPlay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long songId;

    private Long artistId;

    private Long userId;

    private LocalDateTime playedAt;

    public SongPlay() {}

    public SongPlay(Long id, Long songId, Long artistId, Long userId, LocalDateTime playedAt) {
        this.id = id;
        this.songId = songId;
        this.artistId = artistId;
        this.userId = userId;
        this.playedAt = playedAt;
    }

    public static SongPlayBuilder builder() {
        return new SongPlayBuilder();
    }

    public static class SongPlayBuilder {
        private Long id;
        private Long songId;
        private Long artistId;
        private Long userId;
        private LocalDateTime playedAt;

        public SongPlayBuilder id(Long id) { this.id = id; return this; }
        public SongPlayBuilder songId(Long songId) { this.songId = songId; return this; }
        public SongPlayBuilder artistId(Long artistId) { this.artistId = artistId; return this; }
        public SongPlayBuilder userId(Long userId) { this.userId = userId; return this; }
        public SongPlayBuilder playedAt(LocalDateTime playedAt) { this.playedAt = playedAt; return this; }

        public SongPlay build() {
            return new SongPlay(id, songId, artistId, userId, playedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    @PrePersist
    public void onCreate() {
        playedAt = LocalDateTime.now();
    }
}