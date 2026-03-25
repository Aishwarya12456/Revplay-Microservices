package com.revplay.revplay_catalog_service.dto.response;

import com.revplay.revplay_catalog_service.model.Album;
import com.revplay.revplay_catalog_service.model.Song;
import java.util.List;

public class HomeResponse {

    private List<Song> trendingSongs;
    private List<Album> newReleases;
    private List<Album> topAlbums;
    private List<String> genres;

    public HomeResponse() {}

    public HomeResponse(List<Song> trendingSongs, List<Album> newReleases, List<Album> topAlbums, List<String> genres) {
        this.trendingSongs = trendingSongs;
        this.newReleases = newReleases;
        this.topAlbums = topAlbums;
        this.genres = genres;
    }

    public List<Song> getTrendingSongs() { return trendingSongs; }
    public void setTrendingSongs(List<Song> trendingSongs) { this.trendingSongs = trendingSongs; }

    public List<Album> getNewReleases() { return newReleases; }
    public void setNewReleases(List<Album> newReleases) { this.newReleases = newReleases; }

    public List<Album> getTopAlbums() { return topAlbums; }
    public void setTopAlbums(List<Album> topAlbums) { this.topAlbums = topAlbums; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
}
