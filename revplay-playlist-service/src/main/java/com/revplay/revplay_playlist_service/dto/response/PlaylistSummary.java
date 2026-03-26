package com.revplay.revplay_playlist_service.dto.response;

public class PlaylistSummary {

    private Long playlistId;
    private String name;
    private Integer followerCount;
    private Integer songCount;

    public PlaylistSummary() {}

    public PlaylistSummary(Long playlistId, String name, Integer followerCount, Integer songCount) {
        this.playlistId = playlistId;
        this.name = name;
        this.followerCount = followerCount;
        this.songCount = songCount;
    }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getFollowerCount() { return followerCount; }
    public void setFollowerCount(Integer followerCount) { this.followerCount = followerCount; }
    public Integer getSongCount() { return songCount; }
    public void setSongCount(Integer songCount) { this.songCount = songCount; }
}
