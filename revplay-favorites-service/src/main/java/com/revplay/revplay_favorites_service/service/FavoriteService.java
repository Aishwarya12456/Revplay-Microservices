package com.revplay.revplay_favorites_service.service;

import com.revplay.revplay_favorites_service.client.CatalogClient;
import com.revplay.revplay_favorites_service.dto.request.AddFavoriteRequest;
import com.revplay.revplay_favorites_service.model.FavoriteSong;
import com.revplay.revplay_favorites_service.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CatalogClient catalogClient;

    public FavoriteService(FavoriteRepository favoriteRepository, CatalogClient catalogClient) {
        this.favoriteRepository = favoriteRepository;
        this.catalogClient = catalogClient;
    }

    public FavoriteSong addFavorite(Long userId, AddFavoriteRequest request) {
        System.out.println("DEBUG: addFavorite called for userId: " + userId + ", songId: " + request.getSongId());

        if (favoriteRepository.existsByUserIdAndSongId(userId, request.getSongId())) {
            System.out.println("DEBUG: Song already in favorites for userId: " + userId);
            throw new RuntimeException("Song already in favorites");
        }

        FavoriteSong favorite = FavoriteSong.builder()
                .userId(userId)
                .songId(request.getSongId())
                .build();

        FavoriteSong saved = favoriteRepository.save(favorite);
        System.out.println("DEBUG: Successfully saved favorite with id: " + saved.getId());
        return saved;
    }

    public void removeFavorite(Long userId, Long songId) {

        FavoriteSong favorite = favoriteRepository
                .findByUserIdAndSongId(userId, songId)
                .orElseThrow(() -> new RuntimeException("Song not in favorites"));

        favoriteRepository.delete(favorite);
    }


    public List<Object> getMyFavorites(Long userId) {

        List<FavoriteSong> favorites = favoriteRepository.findByUserId(userId);

        List<Long> songIds = favorites.stream()
                .map(FavoriteSong::getSongId)
                .toList();

        return catalogClient.getSongsByIds(songIds);
    }

    public boolean isFavorite(Long userId, Long songId) {

        return favoriteRepository.existsByUserIdAndSongId(userId, songId);

    }

    public Long getFavoriteCount(Long userId) {
        System.out.println("DEBUG: getFavoriteCount called for userId: " + userId);
        Long count = favoriteRepository.countByUserId(userId);
        System.out.println("DEBUG: getFavoriteCount returned: " + count);
        return count;

    }

    public List<Long> getUsersWhoFavorited(Long songId) {
        return favoriteRepository.findBySongId(songId)
                .stream()
                .map(FavoriteSong::getUserId)
                .toList();
    }

    public long countBySongIds(List<Long> songIds) {
        System.out.println("DEBUG: countBySongIds called with songIds: " + songIds);
        if (songIds == null || songIds.isEmpty()) return 0;
        long count = favoriteRepository.countBySongIdIn(songIds);
        System.out.println("DEBUG: countBySongIds returned: " + count);
        return count;
    }
}