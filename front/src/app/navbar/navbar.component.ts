import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  username?: string;
  isDarkMode = false;
  isMenuOpen = false;
  showCampaignMenu: boolean = false;
  isMobileMenuOpen = false;
  isBrowser: boolean;

  constructor(@Inject(PLATFORM_ID) private platformId: Object, public authService: AuthService) {
    this.isBrowser = isPlatformBrowser(this.platformId);
    if (this.isBrowser) {
      const savedTheme = localStorage.getItem('theme');
      const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
      this.isDarkMode = savedTheme === 'dark' || (!savedTheme && prefersDark);
      this.applyTheme();
    }
  }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.authService.getMe().subscribe(user => {
        this.username = user.name;
      });
    }
  }

  toggleDarkMode(): void {
    if (!this.isBrowser) return;

    this.isDarkMode = !this.isDarkMode;
    localStorage.setItem('theme', this.isDarkMode ? 'dark' : 'light');
    this.applyTheme();
  }

  applyTheme(): void {
    if (!this.isBrowser) return;

    const htmlEl = document.documentElement;
    if (this.isDarkMode) {
      htmlEl.classList.add('dark');
    } else {
      htmlEl.classList.remove('dark');
    }
  }

  toggleMobileMenu(): void {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
  }

  closeMobileMenu(): void {
    this.isMobileMenuOpen = false;
  }
}
