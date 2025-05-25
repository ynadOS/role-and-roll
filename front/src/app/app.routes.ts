import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { CampaignFormComponent } from './campaign/components/campaign-form/campaign-form.component';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';
import { AuthGuard } from './guards/auth.guard';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { CampaignOverviewComponent } from './campaign/components/campaign-overview/campaign-overview.component';
import { CampaignPageComponent } from './campaign/components/campaign-page/campaign-page.component';

export const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: '', component: HomeComponent } // ✅ Home avec layout public
    ]
  },
  { path: 'login', component: LoginComponent },     // ✅ Sans layout
  { path: 'register', component: RegisterComponent }, // ✅ Sans layout

  {
    path: '',
    component: DefaultLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'campaigns', component: CampaignOverviewComponent },
      { path: 'campaigns/create', component: CampaignFormComponent },
      { path: 'campaigns/overview', component: CampaignOverviewComponent},
      { path: 'campaigns/:id', component: CampaignPageComponent},
      // { path: 'notifications', component: Notification },
      // 🔒 autres routes privées ici
    ]
  }
];