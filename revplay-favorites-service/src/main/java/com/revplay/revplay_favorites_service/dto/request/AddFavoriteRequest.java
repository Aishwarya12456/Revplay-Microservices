package com.revplay.revplay_favorites_service.dto.request;

public class AddFavoriteRequest {

    private Long songId;

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
