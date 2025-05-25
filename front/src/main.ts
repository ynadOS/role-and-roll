import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { provideHttpClient, withInterceptors, withFetch } from '@angular/common/http';
import { authInterceptorFn } from './app/services/auth.interceptor';

bootstrapApplication(AppComponent, {
  ...appConfig,
  providers: [
    ...(appConfig.providers || []),
    provideHttpClient(withFetch(), withInterceptors([authInterceptorFn]))
    ],
}).catch((err) => console.error(err));
