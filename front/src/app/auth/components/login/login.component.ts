import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true, // ✅ très important
  imports: [
    CommonModule,
    ReactiveFormsModule, // ✅ pour formGroup
    RouterModule,
    HttpClientModule // si tu utilises des requêtes http ici
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loginForm: FormGroup;
  error: string | null = null;
  success: boolean = false;
  isLoading = false;
  countdown = 2;

  constructor() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
    
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.isLoading = true;
      this.error = null;
      this.success = false;

      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          this.authService.saveToken(res.token);
          this.success = true;
          this.startCountdown();
        },
        error: (err) => {
          if (err.status === 401) {
            this.error = 'Identifiants invalides.';
          } else {
            this.error = 'Une erreur est survenue. Veuillez réessayer.';
          }
          this.isLoading = false;
        },
      });
    }
  }

  startCountdown() {
    const interval = setInterval(() => {
      this.countdown--;
      if (this.countdown === 0) {
        clearInterval(interval);
        this.router.navigate(['/']);
      }
    }, 1000);
  }
}
