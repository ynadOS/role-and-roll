import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Pour utiliser router-outlet
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule], // ✅ uniquement ce qui est autorisé
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.authService.getMe().subscribe();
    }
  }
 }