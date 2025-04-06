import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Pour utiliser router-outlet
import { NavbarComponent } from './navbar/navbar.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent { }