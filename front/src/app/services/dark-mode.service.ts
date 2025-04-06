import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DarkModeService {

  constructor() {
    // Vérifier si le mode sombre est enregistré dans le localStorage
    const darkMode = localStorage.getItem('darkMode') === 'enabled';
    if (darkMode) {
      document.documentElement.classList.add('dark');
    }
  }

  // Fonction pour activer/désactiver le mode sombre
  toggleDarkMode(): void {
    const isDarkMode = document.documentElement.classList.toggle('dark');
    localStorage.setItem('darkMode', isDarkMode ? 'enabled' : 'disabled');
  }

  // Fonction pour vérifier si le mode sombre est activé
  isDarkModeEnabled(): boolean {
    return document.documentElement.classList.contains('dark');
  }
}
