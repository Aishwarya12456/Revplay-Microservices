import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ArtistDashboardService {
  private baseUrl = '/revplay';

  constructor(private http: HttpClient) {}

  getDashboardOverview(): Observable<{
    totalSongs: number;
    totalPlays: number;
    totalListeners: number;
    totalAlbums: number;
    totalFavorites: number;
  }> {
    const timestamp = new Date().getTime();
    return this.http.get<{
      totalSongs: number;
      totalPlays: number;
      totalListeners: number;
      totalAlbums: number;
      totalFavorites: number;
    }>(`${this.baseUrl}/analytics/artist/summary?t=${timestamp}`);
  }
}
