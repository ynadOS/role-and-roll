import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal',
  standalone: true,
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
  imports: [CommonModule] // <-- très important ici
})
export class ModalComponent {
  @Input() isOpen = false;
  @Input() title = '';
  @Output() close = new EventEmitter<void>();
}