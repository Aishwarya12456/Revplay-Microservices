package com.revplay.revplay_catalog_service.dto.response;

import org.springframework.data.domain.Page;

public class SearchResponse {

    private String query;

    private PageResponse<SongResponseDTO> songs;

    private PageResponse<ArtistDTO> artists;

    private PageResponse<AlbumResponseDTO> albums;

    private PageResponse<PlaylistResponse> playlists;

    public SearchResponse() {}

    public SearchResponse(String query, PageResponse<SongResponseDTO> songs, PageResponse<ArtistDTO> artists, PageResponse<AlbumResponseDTO> albums, PageResponse<PlaylistResponse> playlists) {
        this.query = query;
        this.songs = songs;
        this.artists = artists;
        this.albums = albums;
        this.playlists = playlists;
    }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public PageResponse<SongResponseDTO> getSongs() { return songs; }
    public void setSongs(PageResponse<SongResponseDTO> songs) { this.songs = songs; }

    public PageResponse<ArtistDTO> getArtists() { return artists; }
    public void setArtists(PageResponse<ArtistDTO> artists) { this.artists = artists; }

    public PageResponse<AlbumResponseDTO> getAlbums() { return albums; }
    public void setAlbums(PageResponse<AlbumResponseDTO> albums) { this.albums = albums; }

    public PageResponse<PlaylistResponse> getPlaylists() { return playlists; }
    public void setPlaylists(PageResponse<PlaylistResponse> playlists) { this.playlists = playlists; }
}
