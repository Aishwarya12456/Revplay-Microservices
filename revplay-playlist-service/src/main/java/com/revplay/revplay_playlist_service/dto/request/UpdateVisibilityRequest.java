package com.revplay.revplay_playlist_service.dto.request;

import com.revplay.revplay_playlist_service.Enum.Visibility;

public class UpdateVisibilityRequest {

    private Visibility visibility;

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
}