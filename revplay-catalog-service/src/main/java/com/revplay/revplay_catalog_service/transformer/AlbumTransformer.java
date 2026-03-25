package com.revplay.revplay_catalog_service.transformer;

import com.revplay.revplay_catalog_service.dto.response.AlbumResponse;
import com.revplay.revplay_catalog_service.dto.response.AlbumResponseDTO;
import com.revplay.revplay_catalog_service.dto.response.ArtistDTO;
import com.revplay.revplay_catalog_service.dto.response.SongResponseDTO;
import com.revplay.revplay_catalog_service.model.Album;

import java.util.List;

public class AlbumTransformer {

    public static AlbumResponse toAlbumResponse(Album savedAlbum, List<SongResponseDTO> songResponseDTOS, ArtistDTO artistDTO) {
        AlbumResponse response = new AlbumResponse();
        response.setId(savedAlbum.getId());
        response.setName(savedAlbum.getName());
        response.setDescription(savedAlbum.getDescription());
        response.setCoverArtUrl(savedAlbum.getCoverImage());
        response.setReleaseDate(savedAlbum.getReleaseDate());
        String artistName = (artistDTO != null && artistDTO.getArtistName() != null) ? artistDTO.getArtistName() : "Unknown Artist";
        response.setArtistName(artistName);
        response.setSongs(songResponseDTOS);
        return response;
    }

    public static AlbumResponseDTO albumToAlbumResponseDTO(Album album) {
        AlbumResponseDTO response = new AlbumResponseDTO();
        response.setId(album.getId());
        response.setName(album.getName());
        return response;
    }
}
