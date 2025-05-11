import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { catchError, switchMap } from 'rxjs/operators';
import { throwError, of, from } from 'rxjs';
import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';

export const authInterceptorFn: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  const clonedRequest = token
    ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
    : req;

  return next(clonedRequest).pipe(
    catchError((error) => {
      if (error.status === 401 && !req.url.includes('/auth/refresh')) {
        return from(
          fetch('/api/auth/refresh', {
            method: 'POST',
            credentials: 'include',
          })
            .then(res => {
              if (!res.ok) throw new Error('Refresh failed');
              return res.json();
            })
        ).pipe(
          switchMap(data => {
            authService.setToken(data.accessToken);
            const retryRequest = req.clone({
              setHeaders: { Authorization: `Bearer ${data.accessToken}` },
            });
            return next(retryRequest);
          }),
          catchError(refreshError => throwError(() => refreshError))
        );
      }

      return throwError(() => error);
    })
  );
};