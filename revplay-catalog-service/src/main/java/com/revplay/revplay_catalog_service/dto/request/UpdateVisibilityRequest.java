package com.revplay.revplay_catalog_service.dto.request;


import com.revplay.revplay_catalog_service.Enum.Visibility;

public class UpdateVisibilityRequest {

    private Visibility visibility;

    public UpdateVisibilityRequest() {}

    public UpdateVisibilityRequest(Visibility visibility) {
        this.visibility = visibility;
    }

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
}