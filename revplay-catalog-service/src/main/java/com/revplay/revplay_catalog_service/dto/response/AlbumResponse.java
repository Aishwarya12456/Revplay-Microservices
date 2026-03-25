package com.revplay.revplay_catalog_service.dto.response;


import java.time.LocalDate;
import java.util.List;

public class AlbumResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private String coverArtUrl;
    private String artistName;
    private List<SongResponseDTO> songs;

    public AlbumResponse() {}

    public AlbumResponse(Long id, String name, String description, LocalDate releaseDate, String coverArtUrl, String artistName, List<SongResponseDTO> songs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.coverArtUrl = coverArtUrl;
        this.artistName = artistName;
        this.songs = songs;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public String getCoverArtUrl() { return coverArtUrl; }
    public void setCoverArtUrl(String coverArtUrl) { this.coverArtUrl = coverArtUrl; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public List<SongResponseDTO> getSongs() { return songs; }
    public void setSongs(List<SongResponseDTO> songs) { this.songs = songs; }
}
