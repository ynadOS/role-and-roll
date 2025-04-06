import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Pour la navigation

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule], // Nécessaire pour routerLink
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  // Optionnel : Des données comme le titre du site
  siteTitle = 'Role & Roll'; 
}

// import { Component, OnInit } from '@angular/core';
// import { RouterModule } from '@angular/router'; // Pour routerLink
// import { AuthService } from '../auth.service'; // Votre service d'authentification (à créer)

// @Component({
//   selector: 'app-navbar',
//   standalone: true,
//   imports: [RouterModule],
//   templateUrl: './navbar.component.html',
//   styleUrl: './navbar.component.css'
// })
// export class NavbarComponent implements OnInit {
//   isLoggedIn = false;

//   constructor(private authService: AuthService) { }

//   ngOnInit() {
//     this.authService.isAuthenticated$.subscribe(loggedIn => {
//       this.isLoggedIn = loggedIn;
//     });
//   }

//   logout() {
//     this.authService.logout();
//   }
// }