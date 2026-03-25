package com.revplay.revplay_analytics_service.service;

import com.revplay.revplay_analytics_service.client.*;
import com.revplay.revplay_analytics_service.dto.request.TrackPlayRequest;
import com.revplay.revplay_analytics_service.dto.response.*;
import com.revplay.revplay_analytics_service.model.SongPlay;
import com.revplay.revplay_analytics_service.repository.SongPlayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final SongPlayRepository songPlayRepository;
    private final CatalogClient catalogClient;
    private final UserClient userClient;
    private final FavoriteClient favoriteClient;
    private final PlaybackClient playbackClient;
    private final PlaylistClient playlistClient;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AnalyticsService.class);

    public AnalyticsService(SongPlayRepository songPlayRepository, CatalogClient catalogClient, UserClient userClient, FavoriteClient favoriteClient, PlaybackClient playbackClient, PlaylistClient playlistClient) {
        this.songPlayRepository = songPlayRepository;
        this.catalogClient = catalogClient;
        this.userClient = userClient;
        this.favoriteClient = favoriteClient;
        this.playbackClient = playbackClient;
        this.playlistClient = playlistClient;
    }

    public void trackSongPlay(TrackPlayRequest request) {

        SongPlay play = SongPlay.builder()
                .songId(request.getSongId())
                .artistId(request.getArtistId())
                .userId(request.getUserId())
                .build();

        songPlayRepository.save(play);
        log.info("Successfully tracked song play: songId={}, artistId={}, userId={}", 
                request.getSongId(), request.getArtistId(), request.getUserId());
    }

    public long getTotalPlaysForArtist(Long artistId) {

        return songPlayRepository.countByArtistId(artistId);

    }

    public List<TopSongResponse> getTopSongs(Long artistId) {

        List<TopSongProjection> topSongs =
                songPlayRepository.findTopSongsByArtist(artistId);

        return topSongs.stream()
                .map(song -> {
                    try {
                        SongResponse catalogSong = catalogClient.getSong(song.getSongId());
                        return TopSongResponse.builder()
                                .songId(song.getSongId())
                                .title(catalogSong != null ? catalogSong.getTitle() : "Unknown Song")
                                .playCount(song.getPlayCount())
                                .build();
                    } catch (Exception e) {
                        log.error("Failed to fetch song details for songId {}: {}", song.getSongId(), e.getMessage());
                        return TopSongResponse.builder()
                                .songId(song.getSongId())
                                .title("Unknown Song")
                                .playCount(song.getPlayCount())
                                .build();
                    }
                })
                .toList();
    }

    public Page<TopListenerResponse> getTopListeners(Long artistId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<TopListenerProjection> listeners =
                songPlayRepository.findTopListenersByArtistId(artistId, pageable);

        List<TopListenerResponse> responses = listeners.stream()
                .map(listener -> {
                    try {
                        UserResponse user = userClient.getUser(listener.getUserId());
                        return TopListenerResponse.builder()
                                .userId(listener.getUserId())
                                .username(user != null ? user.getUsername() : "Unknown User")
                                .playCount(listener.getPlayCount())
                                .build();
                    } catch (Exception e) {
                        log.error("Failed to fetch user details for userId {}: {}", listener.getUserId(), e.getMessage());
                        return TopListenerResponse.builder()
                                .userId(listener.getUserId())
                                .username("Unknown User")
                                .playCount(listener.getPlayCount())
                                .build();
                    }
                })
                .toList();

        return new PageImpl<>(responses, pageable, listeners.getTotalElements());
    }

    public List<PlayTrendResponse> getDailyTrends(Long artistId) {
        return songPlayRepository.getDailyTrends(artistId);
    }

    public List<PlayTrendResponse> getWeeklyTrends(Long artistId) {
        return songPlayRepository.getWeeklyTrends(artistId);
    }

    public List<PlayTrendResponse> getMonthlyTrends(Long artistId) {
        return songPlayRepository.getMonthlyTrends(artistId);
    }

    public ArtistSummaryResponse getArtistSummary(Long artistId) {
        log.info("--- REQUEST: Fetching artist summary for artistId: {} ---", artistId);

        if (artistId == null) {
            log.warn("Artist summary request with NULL artistId. Returning empty stats.");
            return ArtistSummaryResponse.builder()
                    .totalPlays(0L)
                    .totalListeners(0L)
                    .totalSongs(0L)
                    .totalAlbums(0L)
                    .totalFavorites(0L)
                    .build();
        }

        Long totalPlays = songPlayRepository.getTotalPlays(artistId);
        log.info("Artist {}: Total Plays = {}", artistId, totalPlays);

        Long totalListeners = songPlayRepository.getTotalListeners(artistId);
        log.info("Artist {}: Total Listeners = {}", artistId, totalListeners);

        Long totalSongs = 0L;
        try {
            totalSongs = catalogClient.getArtistSongCount(artistId);
        } catch (Exception e) {
            log.error("Failed to fetch song count for artistId {}: {}", artistId, e.getMessage());
        }
        log.info("Artist {}: Total Songs = {}", artistId, totalSongs);

        Long totalAlbums = 0L;
        try {
            totalAlbums = catalogClient.getArtistAlbumCount(artistId);
        } catch (Exception e) {
            log.error("Failed to fetch album count for artistId {}: {}", artistId, e.getMessage());
        }
        log.info("Artist {}: Total Albums = {}", artistId, totalAlbums);

        List<Long> songIds = null;
        try {
            songIds = catalogClient.getArtistSongIds(artistId);
        } catch (Exception e) {
            log.error("Failed to fetch song IDs for artistId {}: {}", artistId, e.getMessage());
        }
        log.info("Artist {}: Song IDs = {}", artistId, songIds);
        
        Long totalFavorites = 0L;
        if (songIds != null && !songIds.isEmpty()) {
            System.out.println("DEBUG: getArtistSummary - requesting favorite count for songIds: " + songIds);
            try {
                totalFavorites = favoriteClient.getSongsFavoriteCount(songIds);
                System.out.println("DEBUG: getArtistSummary - favorite-service returned: " + totalFavorites);
            } catch (Exception e) {
                System.err.println("DEBUG: ERROR fetching favorite count: " + e.getMessage());
                log.error("Failed to fetch favorite count for artistId {}: {}", artistId, e.getMessage());
            }
        } else {
            System.out.println("DEBUG: getArtistSummary - No song IDs found for artist: " + artistId);
        }
        System.out.println("DEBUG: getArtistSummary - final totalFavorites: " + totalFavorites);

        return ArtistSummaryResponse.builder()
                .totalPlays(totalPlays != null ? totalPlays : 0L)
                .totalListeners(totalListeners != null ? totalListeners : 0L)
                .totalSongs(totalSongs != null ? totalSongs : 0L)
                .totalAlbums(totalAlbums != null ? totalAlbums : 0L)
                .totalFavorites(totalFavorites != null ? totalFavorites : 0L)
                .build();
    }

    public UserStatsResponse getUserStats(Long userId){

        Long favoriteSongs = 0L;
        try {
            System.out.println("DEBUG: getUserStats - requesting favorite count for userId: " + userId);
            favoriteSongs = favoriteClient.getFavoriteCount(userId);
            System.out.println("DEBUG: getUserStats - favorite-service returned: " + favoriteSongs);
        } catch (Exception e) {
            System.err.println("DEBUG: ERROR fetching user favorite count: " + e.getMessage());
            log.error("Failed to fetch favorite count for userId {}: {}", userId, e.getMessage());
        }

        Long totalPlayCount = 0L;
        try {
            totalPlayCount = playbackClient.getPlayCount(userId);
        } catch (Exception e) {
            log.error("Failed to fetch play count for userId {}: {}", userId, e.getMessage());
        }

        Long totalPlaylists = 0L;
        try {
            totalPlaylists = playlistClient.getPlaylistCount(userId);
        } catch (Exception e) {
            log.error("Failed to fetch playlist count for userId {}: {}", userId, e.getMessage());
        }

        return UserStatsResponse.builder()
                .favoriteSongs(favoriteSongs != null ? favoriteSongs : 0L)
                .totalPlayCount(totalPlayCount != null ? totalPlayCount : 0L)
                .totalPlaylists(totalPlaylists != null ? totalPlaylists : 0L)
                .build();
    }


    public List<FavoritedUserResponse> getFavoritedUsers(Long songId, Long artistId) {
        try {
            // verify artist owns the song
            SongResponse song = catalogClient.getSong(songId);

            if (song == null || song.getArtistId() == null || !song.getArtistId().equals(artistId)) {
                log.warn("Unauthorized access attempt to song {} by artist {}", songId, artistId);
                return List.of(); // Return empty instead of throwing to prevent 500
            }

            List<Long> userIds = favoriteClient.getUsersWhoFavorited(songId);
            if (userIds == null) return List.of();

            return userIds.stream()
                    .map(id -> {
                        try {
                            UserResponse user = userClient.getUser(id);
                            return FavoritedUserResponse.builder()
                                    .userId(user != null ? user.getId() : id)
                                    .username(user != null ? user.getUsername() : "Unknown User")
                                    .build();
                        } catch (Exception e) {
                            return FavoritedUserResponse.builder()
                                    .userId(id)
                                    .username("Unknown User")
                                    .build();
                        }
                    })
                    .toList();
        } catch (Exception e) {
            log.error("Error in getFavoritedUsers: {}", e.getMessage());
            return List.of();
        }
    }

}