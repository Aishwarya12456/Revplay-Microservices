package com.revplay.revplay_playlist_service.dto.response;

import com.revplay.revplay_playlist_service.Enum.Visibility;

public class PlaylistResponse {

    private Long id;
    private String name;
    private String description;
    private Visibility visibility;
    private Long ownerId;
    private String ownerUsername;
    private int totalSongs;

    public PlaylistResponse() {}

    public PlaylistResponse(Long id, String name, String description, Visibility visibility, Long ownerId, String ownerUsername, int totalSongs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
        this.totalSongs = totalSongs;
    }

    public static PlaylistResponseBuilder builder() {
        return new PlaylistResponseBuilder();
    }

    public static class PlaylistResponseBuilder {
        private Long id;
        private String name;
        private String description;
        private Visibility visibility;
        private Long ownerId;
        private String ownerUsername;
        private int totalSongs;

        public PlaylistResponseBuilder id(Long id) { this.id = id; return this; }
        public PlaylistResponseBuilder name(String name) { this.name = name; return this; }
        public PlaylistResponseBuilder description(String description) { this.description = description; return this; }
        public PlaylistResponseBuilder visibility(Visibility visibility) { this.visibility = visibility; return this; }
        public PlaylistResponseBuilder ownerId(Long ownerId) { this.ownerId = ownerId; return this; }
        public PlaylistResponseBuilder ownerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; return this; }
        public PlaylistResponseBuilder totalSongs(int totalSongs) { this.totalSongs = totalSongs; return this; }

        public PlaylistResponse build() {
            return new PlaylistResponse(id, name, description, visibility, ownerId, ownerUsername, totalSongs);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }
    public int getTotalSongs() { return totalSongs; }
    public void setTotalSongs(int totalSongs) { this.totalSongs = totalSongs; }
}