package com.revplay.revplay_catalog_service.dto.response;

import com.revplay.revplay_catalog_service.model.Album;
import com.revplay.revplay_catalog_service.model.Song;
import java.util.List;

public class ArtistCatalogDTO {

    private Long artistId;
    private List<Album> albums;
    private List<Song> songs;

    public ArtistCatalogDTO() {}

    public ArtistCatalogDTO(Long artistId, List<Album> albums, List<Song> songs) {
        this.artistId = artistId;
        this.albums = albums;
        this.songs = songs;
    }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public List<Album> getAlbums() { return albums; }
    public void setAlbums(List<Album> albums) { this.albums = albums; }

    public List<Song> getSongs() { return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
