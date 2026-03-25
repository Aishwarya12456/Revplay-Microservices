package com.revplay.revplay_catalog_service.dto.response;


public class PlaylistResponse {

    private Long id;

    private String name;

    private String description;

    private String ownerUsername;

    private Long ownerId;

    private int totalSongs;

    private String visibility;

    public PlaylistResponse() {}

    public PlaylistResponse(Long id, String name, String description, String ownerUsername, Long ownerId, int totalSongs, String visibility) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerUsername = ownerUsername;
        this.ownerId = ownerId;
        this.totalSongs = totalSongs;
        this.visibility = visibility;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public int getTotalSongs() { return totalSongs; }
    public void setTotalSongs(int totalSongs) { this.totalSongs = totalSongs; }

    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }
}
