import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  registerForm: FormGroup;
  error: string | null = null;
  success = false;
  isLoading = false;
  countdown = 3;
  
  constructor() {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      password_confirmation: ['', Validators.required],
    });    
  }
  hasError(controlName: string, errorType: string): boolean {
    const control = this.registerForm.get(controlName);
    return !!control && control.hasError(errorType) && control.touched;
  }
  


  onSubmit() {
    if (this.registerForm.valid) {
      const { name, email, password, password_confirmation } = this.registerForm.value;

      if (password !== password_confirmation) {
        this.error = 'Les mots de passe ne correspondent pas.';
        return;
      }

      this.isLoading = true;
      this.authService.register({ name, email, password }).subscribe({
        next: () => {
          this.success = true;
          this.startCountdown();
        },
        error: () => {
          this.error = 'Erreur lors de l’inscription. Veuillez réessayer.';
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
        this.router.navigate(['/login']);
      }
    }, 1000);
  }
}
