package com.revplay.revplay_playlist_service.dto.request;

import com.revplay.revplay_playlist_service.Enum.Visibility;

public class UpdatePlaylistRequest {

    private String name;
    private String description;
    private Visibility visibility;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
}