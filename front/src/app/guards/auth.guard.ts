import { Injectable, inject } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  private auth = inject(AuthService);
  private router = inject(Router);


  canActivate(): Observable<boolean> {
    const token = this.auth.getToken();
    
    if (!token) {
      return of(false); // Laisse Angular rediriger automatiquement
    }
  
    return this.auth.getMe().pipe(
      map((user) => {
        return !!user; // true si user existe, sinon false
      }),
      catchError(() => {
        return of(false); // Si erreur serveur, accès refusé
      })
    );
  }
}