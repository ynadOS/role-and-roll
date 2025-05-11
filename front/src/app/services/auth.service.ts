import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, BehaviorSubject, ReplaySubject, from, of, throwError } from 'rxjs';
import { tap, switchMap, catchError } from 'rxjs/operators';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';
import { environment } from '../../environments/environments';


export interface AuthRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private platformId = inject(PLATFORM_ID);

  authCheckInProgress = new BehaviorSubject<boolean>(true);
  currentUser: any = null;

  private refreshInProgress = false;
  private refreshTokenSubject = new ReplaySubject<string>(1);

  constructor(private http: HttpClient, private router: Router) {}

  login(data: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data);
  }

  register(data: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  saveToken(token: string) {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('token', token);
    }
  }

  setToken(token: string) {
    this.saveToken(token);
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null;
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
    }
    this.currentUser = null;
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUserId(): number | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.userId || null;
    } catch (e) {
      return null;
    }
  }
  forceLogout() {
    this.logout();
    this.router.navigate(['/login']);
  }

  getMe(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/me`);
  }

  refreshAccessToken(): Observable<string> {
    if (this.refreshInProgress) {
      return this.refreshTokenSubject.asObservable();
    }

    this.refreshInProgress = true;

    return from(
      fetch(`${this.apiUrl}/refresh`, {
        method: 'POST',
        credentials: 'include',
      }).then(res => {
        if (!res.ok) throw new Error('Refresh failed');
        return res.json();
      })
    ).pipe(
      tap(data => {
        this.setToken(data.token);
        this.refreshTokenSubject.next(data.token);
        this.refreshInProgress = false;
      }),
      switchMap(data => of(data.token)),
      catchError(err => {
        this.refreshInProgress = false;
        return throwError(() => err);
      })
    );
  }

}
