import { Component } from '@angular/core';
import { SidebarService } from '../../../core/services/sidebar.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.html',
  styleUrls: ['./sidebar.scss']
})
export class SidebarComponent {
  constructor(public sidebarService: SidebarService) {}
}
