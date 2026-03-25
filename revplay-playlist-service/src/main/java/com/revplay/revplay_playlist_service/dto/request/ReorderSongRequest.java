package com.revplay.revplay_playlist_service.dto.request;

public class ReorderSongRequest {

    private Long songId;
    private int newPosition;

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public int getNewPosition() { return newPosition; }
    public void setNewPosition(int newPosition) { this.newPosition = newPosition; }
}