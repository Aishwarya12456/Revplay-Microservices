package com.revplay.revplay_favorites_service.controller;

import com.revplay.revplay_favorites_service.dto.request.AddFavoriteRequest;
import com.revplay.revplay_favorites_service.model.FavoriteSong;

import com.revplay.revplay_favorites_service.service.FavoriteService;
import com.revplay.revplay_security.model.JwtUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revplay/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('LISTENER', 'ARTIST')")
    public ResponseEntity<FavoriteSong> addFavorite(
            @RequestBody AddFavoriteRequest request,
            Authentication authentication) {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        Long userId = jwtUser.getUserId();

        return ResponseEntity.ok(
                favoriteService.addFavorite(userId, request)
        );
    }

    @DeleteMapping("/{songId}")
    @PreAuthorize("hasAnyRole('LISTENER', 'ARTIST')")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable Long songId,
            Authentication authentication) {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        Long userId = jwtUser.getUserId();

        favoriteService.removeFavorite(userId, songId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LISTENER', 'ARTIST')")
    public ResponseEntity<List<Object>> getMyFavorites(
            Authentication authentication) {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        Long userId = jwtUser.getUserId();

        return ResponseEntity.ok(
                favoriteService.getMyFavorites(userId)
        );
    }

    @GetMapping("/check/{songId}")
    @PreAuthorize("hasAnyRole('LISTENER', 'ARTIST')")
    public ResponseEntity<Boolean> isFavorite(
            @PathVariable Long songId,
            Authentication authentication) {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        Long userId = jwtUser.getUserId();

        return ResponseEntity.ok(
                favoriteService.isFavorite(userId, songId)
        );
    }

    @GetMapping("/user/{userId}/count")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getFavoriteCount(@PathVariable Long userId) {

        return ResponseEntity.ok(
                favoriteService.getFavoriteCount(userId)
        );
    }

    @GetMapping("/song/{songId}/users")
    public ResponseEntity<List<Long>> getUsersWhoFavorited(
            @PathVariable Long songId) {

        return ResponseEntity.ok(
                favoriteService.getUsersWhoFavorited(songId)
        );
    }

    @GetMapping("/songs/count")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getSongsFavoriteCount(@RequestParam("songIds") List<Long> songIds) {
        return ResponseEntity.ok(favoriteService.countBySongIds(songIds));
    }
}
