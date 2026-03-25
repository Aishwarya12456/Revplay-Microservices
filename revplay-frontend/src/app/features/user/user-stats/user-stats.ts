import { Component, OnInit, OnDestroy, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MusicService } from '../../../core/services/music';

@Component({
  selector: 'app-user-stats',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-stats.html',
  styleUrls: ['./user-stats.scss'],
  changeDetection: ChangeDetectionStrategy.Default,
})
export class UserStatsComponent implements OnInit, OnDestroy {
  totalPlaylists = 0;
  totalFavorites = 0;
  totalPlayCount = 0;
  private pollSubscription?: any;
  loading = true;

  constructor(
    private musicService: MusicService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    console.log('Starting User Statistics Polling...');
    this.loadStats();
    
    // Refresh stats every 10 seconds
    this.pollSubscription = setInterval(() => {
      this.loadStats();
    }, 10000);
  }

  ngOnDestroy(): void {
    if (this.pollSubscription) {
      clearInterval(this.pollSubscription);
    }
  }

  loadStats(): void {
    this.musicService.getUserStatistics().subscribe({
      next: (res) => {
        this.totalPlaylists = res.totalPlaylists || 0;
        this.totalFavorites = res.favoriteSongs || 0;
        this.totalPlayCount = res.totalPlayCount || 0;

        this.loading = false;
        this.cdr.markForCheck();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load user statistics', err);
        this.loading = false;
        this.cdr.markForCheck();
        this.cdr.detectChanges();
      },
    });
  }
}
