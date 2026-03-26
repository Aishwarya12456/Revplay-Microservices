package com.revplay.revplay_playlist_service.model;

import com.revplay.revplay_playlist_service.Enum.Visibility;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

    @Column(columnDefinition = "integer default 0")
    private Integer followerCount = 0;

    private LocalDateTime createdAt;

    public Playlist() {}

    public Playlist(Long id, String name, String description, Long userId, Visibility visibility, Integer followerCount, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.visibility = visibility;
        this.followerCount = followerCount;
        this.createdAt = createdAt;
    }

    public static PlaylistBuilder builder() {
        return new PlaylistBuilder();
    }

    public static class PlaylistBuilder {
        private Long id;
        private String name;
        private String description;
        private Long userId;
        private Visibility visibility;
        private Integer followerCount;
        private LocalDateTime createdAt;

        public PlaylistBuilder id(Long id) { this.id = id; return this; }
        public PlaylistBuilder name(String name) { this.name = name; return this; }
        public PlaylistBuilder description(String description) { this.description = description; return this; }
        public PlaylistBuilder userId(Long userId) { this.userId = userId; return this; }
        public PlaylistBuilder visibility(Visibility visibility) { this.visibility = visibility; return this; }
        public PlaylistBuilder followerCount(Integer followerCount) { this.followerCount = followerCount; return this; }
        public PlaylistBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Playlist build() {
            return new Playlist(id, name, description, userId, visibility, followerCount, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
    public Integer getFollowerCount() { return followerCount; }
    public void setFollowerCount(Integer followerCount) { this.followerCount = followerCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}