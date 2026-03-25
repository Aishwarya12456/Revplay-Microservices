import { Component, OnInit, OnDestroy, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArtistDashboardService } from '../../services/artist-dashboard';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard-overview',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-overview.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DashboardOverview implements OnInit {

  loading = false;

  totalSongs = 0;
  totalPlays = 0;
  totalListeners = 0;
  totalAlbums = 0;
  totalFavorites = 0;

  private pollSubscription?: Subscription;

  constructor(
    private artistDashboardService: ArtistDashboardService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadOverview(true);
    // Poll every 5 seconds for real-time updates
    this.pollSubscription = interval(5000).subscribe(() => {
      this.loadOverview(false);
    });
  }

  ngOnDestroy(): void {
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
  }

  loadOverview(isInitial: boolean = false) {
    if (isInitial) {
      this.loading = true;
      this.cdr.markForCheck();
    }

    this.artistDashboardService.getDashboardOverview().subscribe({
      next: (res) => {
        console.log('Received Overview Data:', res); // Debug log
        
        this.totalSongs = res.totalSongs || 0;
        this.totalPlays = res.totalPlays || 0;
        this.totalListeners = res.totalListeners || 0;
        this.totalAlbums = res.totalAlbums || 0;
        this.totalFavorites = res.totalFavorites || 0;
        
        this.loading = false;
        this.cdr.markForCheck(); // Use markForCheck for OnPush
        this.cdr.detectChanges(); // Force immediate update
      },
      error: (err) => {
        console.error('Failed to load dashboard overview', err);
        this.loading = false;
        this.cdr.markForCheck();
        this.cdr.detectChanges();
      },
    });
  }
}