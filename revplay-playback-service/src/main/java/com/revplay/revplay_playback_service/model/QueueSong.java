package com.revplay.revplay_playback_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "queue_songs")
public class QueueSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;

    private int position;

    private LocalDateTime addedAt;

    public QueueSong() {}

    public QueueSong(Long id, Long userId, Long songId, int position, LocalDateTime addedAt) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.position = position;
        this.addedAt = addedAt;
    }

    public static QueueSongBuilder builder() {
        return new QueueSongBuilder();
    }

    public static class QueueSongBuilder {
        private Long id;
        private Long userId;
        private Long songId;
        private int position;
        private LocalDateTime addedAt;

        public QueueSongBuilder id(Long id) { this.id = id; return this; }
        public QueueSongBuilder userId(Long userId) { this.userId = userId; return this; }
        public QueueSongBuilder songId(Long songId) { this.songId = songId; return this; }
        public QueueSongBuilder position(int position) { this.position = position; return this; }
        public QueueSongBuilder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public QueueSong build() {
            return new QueueSong(id, userId, songId, position, addedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }

    @PrePersist
    public void onCreate() {
        addedAt = LocalDateTime.now();
    }
}