import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { timeout } from 'rxjs/operators';
import { ListeningHistoryResponse } from '../models/listening-history';
import { PagedResult, SearchResponse, SongResponse } from '../models/serach';

@Injectable({
  providedIn: 'root',
})
export class MusicService {
  constructor(private http: HttpClient) {}

  private cachedSongs: any[] = [];
  private cachedPage = -1;

  baseUrl = '/revplay';
  authUrl = '/revplay/auth';
  catalogUrl = '/revplay/catalog';
  playlistUrl = '/revplay';
  playbackUrl = '/revplay/playback';
  favoritesUrl = '/revplay/favorites';
  analyticsUrl = '/revplay/analytics';

  getSongs(page: number, size: number, sortBy: string, direction: string) {
    if (this.cachedSongs.length > 0 && this.cachedPage === page) {
      return of({
        content: this.cachedSongs,
        totalPages: 1,
        totalElements: this.cachedSongs.length,
      });
    }

    return this.http
      .get<any>(
        `${this.catalogUrl}/get-all-songs?page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`,
      )
      .pipe(
        timeout(10000),
        tap((res) => {
          this.cachedSongs = res.content;
          this.cachedPage = page;
        }),
      );
  }

  playSong(songId: number) {
    return this.http.post(`${this.playbackUrl}/play/${songId}`, {});
  }

  pauseSong() {
    return this.http.post(`${this.playbackUrl}/pause`, {});
  }

  markFavorite(songId: number) {
    return this.http.post(`${this.favoritesUrl}`, { songId });
  }

  removeFavorite(songId: number) {
    return this.http.delete(`${this.favoritesUrl}/${songId}`);
  }

  getFavorites() {
    return this.http.get<any[]>(`${this.favoritesUrl}`);
  }

  getPlaylists(page: number, size: number) {
    return this.http.get<any>(
      `${this.playlistUrl}/playlists?page=${page}&size=${size}&sortBy=id&direction=asc`,
    );
  }

  createPlaylist(payload: any) {
    return this.http.post(`${this.playlistUrl}/playlists`, payload);
  }

  deletePlaylist(id: number) {
    return this.http.delete(`${this.playlistUrl}/playlists/${id}`);
  }

  addSongToPlaylist(playlistId: number, songId: number) {
    return this.http.post(`${this.playlistUrl}/playlists/${playlistId}/songs`, {
      songId: songId,
    });
  }

  removeSongFromPlaylist(playlistId: number, songId: number) {
    return this.http.delete(`${this.playlistUrl}/playlists/${playlistId}/songs/${songId}`);
  }

  getPlaylistById(id: number) {
    return this.http.get<any>(`${this.playlistUrl}/playlists/${id}/songs`);
  }

  updatePlaylistPrivacy(id: number, visibility: string) {
    return this.http.patch(`${this.playlistUrl}/playlists/${id}/visibility`, {
      visibility: visibility,
    });
  }

  updatePlaylist(id: number, data: any) {
    return this.http.put(`${this.playlistUrl}/playlists/${id}`, data);
  }

  getPublicPlaylists(page = 0, size = 20) {
    return this.http.get(
      `${this.playlistUrl}/playlists/public/paged?page=${page}&size=${size}&sortBy=createdAt&direction=desc`,
    );
  }

  followPlaylist(id: number) {
    return this.http.post(`${this.playlistUrl}/playlists/${id}/follow`, {});
  }

  unfollowPlaylist(id: number) {
    return this.http.delete(`${this.playlistUrl}/playlists/${id}/follow`);
  }

  getRecentlyPlayed() {
    return this.http.get<ListeningHistoryResponse[]>(`${this.playbackUrl}/recent`);
  }

  getListeningHistory(page = 0, size = 20) {
    return this.http.get<{
      content: ListeningHistoryResponse[];
      totalPages: number;
    }>(`${this.playbackUrl}/history`);
  }

  clearHistory() {
    return this.http.delete(`${this.playbackUrl}/clear`);
  }

  search(query: string, page = 0, size = 10) {
    return this.http.get<SearchResponse>(`${this.catalogUrl}/songs/search`, {
      params: {
        q: query,
        page: page,
        size: size,
      },
    });
  }

  getSongsByGenre(genre: string, page = 0, size = 10) {
    return this.http.get<PagedResult<SongResponse>>(`${this.catalogUrl}/songs/genre/${genre}`, {
      params: { page, size },
    });
  }

  getAllArtists() {
    return this.http.get<any[]>(`${this.authUrl}/artist/get-all`);
  }

  getUserStatistics() {
    const timestamp = new Date().getTime();
    return this.http.get<{
      totalPlaylists: number;
      favoriteSongs: number;
      totalPlayCount: number;
    }>(`${this.analyticsUrl}/user/stats?t=${timestamp}`);
  }

  deleteSong(songId: number) {
    return this.http.delete(`${this.catalogUrl}/songs/${songId}`);
  }
}
