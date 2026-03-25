package com.revplay.revplay_playlist_service.dto.response;

import java.util.List;

public class PlaylistDetailsResponse {

    private PlaylistResponse playlist;
    private List<Object> songs;

    public PlaylistDetailsResponse() {}

    public PlaylistDetailsResponse(PlaylistResponse playlist, List<Object> songs) {
        this.playlist = playlist;
        this.songs = songs;
    }

    public static PlaylistDetailsResponseBuilder builder() {
        return new PlaylistDetailsResponseBuilder();
    }

    public static class PlaylistDetailsResponseBuilder {
        private PlaylistResponse playlist;
        private List<Object> songs;

        public PlaylistDetailsResponseBuilder playlist(PlaylistResponse playlist) { this.playlist = playlist; return this; }
        public PlaylistDetailsResponseBuilder songs(List<Object> songs) { this.songs = songs; return this; }

        public PlaylistDetailsResponse build() {
            return new PlaylistDetailsResponse(playlist, songs);
        }
    }

    public PlaylistResponse getPlaylist() { return playlist; }
    public void setPlaylist(PlaylistResponse playlist) { this.playlist = playlist; }
    public List<Object> getSongs() { return songs; }
    public void setSongs(List<Object> songs) { this.songs = songs; }
}
