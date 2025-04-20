import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { StatusService } from '../../../services/status.service';
import { Campaign, CampaignService } from '../../../services/campaigns.service';
import { UniversesService } from '../../../services/universes.service';

interface Universe {
  id: number;
  name: string;
  description: string;
}

interface Status {
  id: number;
  name: string;
}

@Component({
  selector: 'app-campaign-form',
  standalone: true,
  templateUrl: './campaign-form.component.html',
  styleUrls: ['./campaign-form.component.css'],
  imports: [FormsModule, CommonModule]
})
export class CampaignFormComponent implements OnInit {
  title = '';
  description = '';
  selectedUniverseId: number | null = null;
  selectedStatusId: number | null = null;
  statuses: Status[] = [];
  universes: Universe[] = [];

  isSubmitting = false;
  error = '';
  creationSuccess = false;

  constructor(
    private campaignService: CampaignService,
    private authService: AuthService,
    private universeService: UniversesService,
    private statusService: StatusService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.statusService.getStatuses().subscribe({
      next: (data: Status[]) => {
        this.statuses = data;
        if (data.length > 0) {
          this.selectedStatusId = data[0].id;
        }
      },
      error: (err) => {
        console.error('Erreur chargement des statuts :', err);
      }
    });
    
    this.universeService.getUniverses().subscribe({
      next: (data: Universe[]) => {
        this.universes = data;
      },
      error: (err) => {
        console.error('Erreur chargement des universes :', err);
      }
    });
  }

  onSubmit(): void {
    const userId = this.authService.getUserId();
    if (!userId) {
      this.error = 'Utilisateur non authentifié.';
      return;
    }
    
    const campaign: Campaign = {
      title: this.title,
      description: this.description,
      userId,
      statusId: this.selectedStatusId!,
      universeId: this.selectedUniverseId || null
    };
    
    this.isSubmitting = true;
    this.campaignService.createCampaign(campaign).subscribe({
      next: () => {
        this.creationSuccess = true;
        setTimeout(() => this.router.navigate(['/']), 1500);
      },
      error: (err) => {
        console.error(err);
        this.error = 'Erreur lors de la création de la campagne.';
        this.isSubmitting = false;
      }
    });
    
  }
}
