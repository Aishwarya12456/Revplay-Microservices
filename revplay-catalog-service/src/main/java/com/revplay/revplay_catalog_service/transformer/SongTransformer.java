package com.revplay.revplay_catalog_service.transformer;

import com.revplay.revplay_catalog_service.client.UserClient;
import com.revplay.revplay_catalog_service.dto.response.ArtistDTO;
import com.revplay.revplay_catalog_service.dto.response.SongResponse;
import com.revplay.revplay_catalog_service.dto.response.SongResponseDTO;
import com.revplay.revplay_catalog_service.model.Album;
import com.revplay.revplay_catalog_service.model.Song;
import com.revplay.revplay_catalog_service.repository.AlbumRepository;
import org.springframework.stereotype.Component;

@Component
public class SongTransformer {

    private final AlbumRepository albumRepository;
    private final UserClient userClient;

    public SongTransformer(AlbumRepository albumRepository, UserClient userClient) {
        this.albumRepository = albumRepository;
        this.userClient = userClient;
    }

    public SongResponseDTO songToSongResponseDTO(Song song){

        String albumName = null;

        if (song.getAlbumId() != null) {
            Album album = albumRepository.findById(song.getAlbumId())
                    .orElse(null);

            if (album != null) {
                albumName = album.getName();
            }
        }

        String artistName = "Unknown Artist";
        try {
            ArtistDTO artist = userClient.getArtist(song.getArtistId());
            if (artist != null && artist.getArtistName() != null) {
                artistName = artist.getArtistName();
            }
        } catch (Exception e) {
            System.err.println("Error fetching artist name for id " + song.getArtistId() + ": " + e.getMessage());
        }

        SongResponseDTO dto = new SongResponseDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setGenre(song.getGenre());
        dto.setDuration(song.getDuration());
        dto.setAudioUrl(song.getAudioUrl());
        dto.setCoverArtUrl(song.getCoverImage());
        dto.setArtistId(song.getArtistId());
        dto.setArtistName(artistName);
        dto.setAlbumId(song.getAlbumId());
        dto.setAlbumName(albumName);
        dto.setVisibility(song.getVisibility());
        return dto;
    }

}