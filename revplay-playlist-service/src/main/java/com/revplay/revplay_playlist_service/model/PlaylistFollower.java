package com.revplay.revplay_playlist_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_followers")
public class PlaylistFollower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playlistId;

    private Long userId;

    private LocalDateTime followedAt;

    public PlaylistFollower() {}

    public PlaylistFollower(Long id, Long playlistId, Long userId, LocalDateTime followedAt) {
        this.id = id;
        this.playlistId = playlistId;
        this.userId = userId;
        this.followedAt = followedAt;
    }

    public static PlaylistFollowerBuilder builder() {
        return new PlaylistFollowerBuilder();
    }

    public static class PlaylistFollowerBuilder {
        private Long id;
        private Long playlistId;
        private Long userId;
        private LocalDateTime followedAt;

        public PlaylistFollowerBuilder id(Long id) { this.id = id; return this; }
        public PlaylistFollowerBuilder playlistId(Long playlistId) { this.playlistId = playlistId; return this; }
        public PlaylistFollowerBuilder userId(Long userId) { this.userId = userId; return this; }
        public PlaylistFollowerBuilder followedAt(LocalDateTime followedAt) { this.followedAt = followedAt; return this; }

        public PlaylistFollower build() {
            return new PlaylistFollower(id, playlistId, userId, followedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDateTime getFollowedAt() { return followedAt; }
    public void setFollowedAt(LocalDateTime followedAt) { this.followedAt = followedAt; }

    @PrePersist
    public void onCreate() {
        followedAt = LocalDateTime.now();
    }
}