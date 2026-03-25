import { SidebarComponent } from '../../../shared/components/sidebar/sidebar';
import { NavbarComponent } from '../../../shared/components/navbar/navbar';
import { PlayerBarComponent } from '../../../shared/components/player-bar/player-bar';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../../services/sidebar.service';


@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    SidebarComponent,
    NavbarComponent,
    PlayerBarComponent  
  ],
  templateUrl: './main-layout.html'
})
export class MainLayoutComponent {
  constructor(public sidebarService: SidebarService) {}
}