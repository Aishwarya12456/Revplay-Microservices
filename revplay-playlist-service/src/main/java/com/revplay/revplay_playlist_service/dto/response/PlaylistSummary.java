package com.revplay.revplay_playlist_service.dto.response;

public class PlaylistSummary {

    private Long playlistId;
    private String name;
    private int followerCount;
    private int songCount;

    public PlaylistSummary() {}

    public PlaylistSummary(Long playlistId, String name, int followerCount, int songCount) {
        this.playlistId = playlistId;
        this.name = name;
        this.followerCount = followerCount;
        this.songCount = songCount;
    }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getFollowerCount() { return followerCount; }
    public void setFollowerCount(int followerCount) { this.followerCount = followerCount; }
    public int getSongCount() { return songCount; }
    public void setSongCount(int songCount) { this.songCount = songCount; }
}
