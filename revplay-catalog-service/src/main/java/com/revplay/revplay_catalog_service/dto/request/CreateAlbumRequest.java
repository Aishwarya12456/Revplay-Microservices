package com.revplay.revplay_catalog_service.dto.request;

import com.revplay.revplay_catalog_service.Enum.Genre;

import java.time.LocalDate;

public class CreateAlbumRequest {

    private String name;
    private String description;
    private Genre genre;
    private String releaseDate;

    public CreateAlbumRequest() {}

    public CreateAlbumRequest(String name, String description, Genre genre, String releaseDate) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}
