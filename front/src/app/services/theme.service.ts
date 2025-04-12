import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private darkModeKey = 'darkMode';

  constructor() {
    this.initializeTheme();
  }

  initializeTheme(): void {
    const prefersDark = localStorage.getItem(this.darkModeKey) === 'enabled';
    document.documentElement.classList.toggle('dark', prefersDark);
  }

  toggleTheme(): void {
    const isDark = document.documentElement.classList.toggle('dark');
    localStorage.setItem(this.darkModeKey, isDark ? 'enabled' : 'disabled');
  }

  isDarkMode(): boolean {
    return document.documentElement.classList.contains('dark');
  }
}
