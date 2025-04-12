import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { CampaignFormComponent } from './campaign/components/campaign-form/campaign-form.component';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';
import { AuthGuard } from './guards/auth.guard'; // ✅ importe le guard

export const routes: Routes = [
  {
    path: '',
    component: DefaultLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      {
        path: 'campaigns',
        component: CampaignFormComponent,
        canActivate: [AuthGuard] // ✅ protégé
      },
      // Tu peux ajouter d'autres routes privées ici
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];
