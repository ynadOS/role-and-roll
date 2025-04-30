import { Component, Input, Output, EventEmitter, HostListener, ElementRef, ViewChild, AfterViewInit, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal',
  standalone: true,
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
  imports: [CommonModule]
})
export class ModalComponent implements AfterViewInit, OnChanges {
  @Input() isOpen = false;
  @Input() title = '';
  @Input() backdropClosable = true;
  @Output() close = new EventEmitter<void>();
  @Input() escClosable: boolean = true;

  @ViewChild('modalContainer') modalContainerRef!: ElementRef;
  @ViewChild('backdrop') backdropRef!: ElementRef; // Référence pour le backdrop

  ngAfterViewInit(): void {
    if (this.isOpen) {
      this.focusModal();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['isOpen']?.currentValue) {
      // Délai pour s'assurer que la modal est dans le DOM avant de tenter de la focusser
      setTimeout(() => this.focusModal(), 0);
    }
  }

  private focusModal(): void {
    if (this.modalContainerRef) {
      this.modalContainerRef.nativeElement.focus();
    }
  }

  emitClose(): void {
    if (this.close.observed) {
      this.close.emit();
    }
  }

  onBackdropClick(event: MouseEvent): void {
    // Vérifie si l'élément cliqué est bien le backdrop lui-même
    if (this.backdropClosable && this.backdropRef?.nativeElement === event.target) {
      this.emitClose();
    }
  }

  @HostListener('document:keydown.escape', ['$event'])
  handleEscape(event: KeyboardEvent) {
    if (this.isOpen && this.escClosable) {
      this.emitClose();
    }
  }
}