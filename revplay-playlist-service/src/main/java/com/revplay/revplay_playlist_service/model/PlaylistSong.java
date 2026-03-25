package com.revplay.revplay_playlist_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_songs")
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playlistId;

    private Long songId;

    private int position;

    private LocalDateTime addedAt;

    public PlaylistSong() {}

    public PlaylistSong(Long id, Long playlistId, Long songId, int position, LocalDateTime addedAt) {
        this.id = id;
        this.playlistId = playlistId;
        this.songId = songId;
        this.position = position;
        this.addedAt = addedAt;
    }

    public static PlaylistSongBuilder builder() {
        return new PlaylistSongBuilder();
    }

    public static class PlaylistSongBuilder {
        private Long id;
        private Long playlistId;
        private Long songId;
        private int position;
        private LocalDateTime addedAt;

        public PlaylistSongBuilder id(Long id) { this.id = id; return this; }
        public PlaylistSongBuilder playlistId(Long playlistId) { this.playlistId = playlistId; return this; }
        public PlaylistSongBuilder songId(Long songId) { this.songId = songId; return this; }
        public PlaylistSongBuilder position(int position) { this.position = position; return this; }
        public PlaylistSongBuilder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public PlaylistSong build() {
            return new PlaylistSong(id, playlistId, songId, position, addedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
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
