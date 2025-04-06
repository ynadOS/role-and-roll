import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { CampaignFormComponent } from './campaign/components/campaign-form/campaign-form.component';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component'; // Importez le layout

export const routes: Routes = [
  {
    path: '',
    component: DefaultLayoutComponent, // Utiliser le layout ici
    children: [
      { path: '', component: HomeComponent },
      { path: 'campaigns', component: CampaignFormComponent },
      // ... autres routes avec la navbar
    ]
  },
  { path: 'login', component: LoginComponent },  // Routes sans la navbar
  { path: 'register', component: RegisterComponent },
  // ... autres routes sans la navbar
];