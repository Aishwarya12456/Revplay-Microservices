package com.revplay.revplay_favorites_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "favorite_songs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "songId"})
        }
)
public class FavoriteSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long songId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime addedAt;

    public FavoriteSong() {}

    public FavoriteSong(Long id, Long userId, Long songId, LocalDateTime addedAt) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.addedAt = addedAt;
    }

    public static FavoriteSongBuilder builder() {
        return new FavoriteSongBuilder();
    }

    public static class FavoriteSongBuilder {
        private Long id;
        private Long userId;
        private Long songId;
        private LocalDateTime addedAt;

        public FavoriteSongBuilder id(Long id) { this.id = id; return this; }
        public FavoriteSongBuilder userId(Long userId) { this.userId = userId; return this; }
        public FavoriteSongBuilder songId(Long songId) { this.songId = songId; return this; }
        public FavoriteSongBuilder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public FavoriteSong build() {
            return new FavoriteSong(id, userId, songId, addedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }

    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
    }
}