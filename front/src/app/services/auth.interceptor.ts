import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { catchError, switchMap } from 'rxjs/operators';
import { throwError, of } from 'rxjs';
import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { environment } from '../../environments/environments';


export const authInterceptorFn: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const authService = inject(AuthService);
  const token = authService.getToken();
  console.log("Token utilisé dans Authorization:", token);

  const clonedRequest = token
    ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
    : req;
  const finalRequest = clonedRequest.clone({ withCredentials: true });

  return next(finalRequest).pipe(
    catchError((error) => {
      if (error.status === 401 && !req.url.includes('/auth/refresh')) {
        return authService.refreshAccessToken().pipe(
          switchMap(token => {
            const retryRequest = req.clone({
              setHeaders: { Authorization: `Bearer ${token}` },
              withCredentials: true,
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