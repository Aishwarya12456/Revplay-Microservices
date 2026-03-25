package com.revplay.revplay_user_service.transformer;

import com.revplay.revplay_user_service.dto.request.ArtistProfileRequest;
import com.revplay.revplay_user_service.dto.request.RegisterRequest;
import com.revplay.revplay_user_service.dto.response.ArtistDTO;
import com.revplay.revplay_user_service.dto.response.ArtistResponse;
import com.revplay.revplay_user_service.model.Artist;
import com.revplay.revplay_user_service.model.User;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArtistTransformer {

    public ArtistResponse artistToArtistResponse(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .genre(artist.getGenre())
                .bio(artist.getBio())
                .bannerImage(artist.getBannerImage())
                .profilePicture(artist.getProfilePicture())
                .instagram(artist.getInstagram())
                .spotify(artist.getSpotify())
                .twitter(artist.getTwitter())
                .youtube(artist.getYoutube())
                .website(artist.getWebsite())
                .build();
    }

    public Artist setArtistProfile(Artist artist, User user, ArtistProfileRequest request) {
        artist.setUser(user);
        artist.setArtistName(request.getArtistName());
        artist.setBio(request.getBio());
        artist.setGenre(request.getGenre());
        artist.setInstagram(request.getInstagram());
        artist.setTwitter(request.getTwitter());
        artist.setYoutube(request.getYoutube());
        artist.setSpotify(request.getSpotify());
        artist.setWebsite(request.getWebsite());
        return artist;
    }

    public Artist artistRequestToArtist(RegisterRequest artistRequest, User user) {
        Artist artist = new Artist();
        artist.setArtistName(artistRequest.getUsername());
        artist.setProfilePicture(user.getProfilePicture());
        artist.setUser(user);
        return artist;
    }

    public ArtistDTO artistToArtistDTO(Artist artist) {
        return ArtistDTO.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .bio(artist.getBio())
                .profilePicture(artist.getProfilePicture())
                .genre(String.valueOf(artist.getGenre()))
                .build();
    }
}
