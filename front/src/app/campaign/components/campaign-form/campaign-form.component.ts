import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Campaign, CampaignService } from '../../../services/campaigns.service';
import { UniversesService } from '../../../services/universes.service';

interface Universe {
  id: number;
  name: string;
  description: string;
}

@Component({
  selector: 'app-campaign-form',
  standalone: true,
  templateUrl: './campaign-form.component.html',
  styleUrls: ['./campaign-form.component.css'],
  imports: [FormsModule, CommonModule, RouterModule]
})
export class CampaignFormComponent implements OnInit, OnChanges {
  @Input() campaign?: Campaign;
  @Output() formSubmitted = new EventEmitter<void>();
  @Output() campaignUpdated = new EventEmitter<void>();

  title = '';
  description = '';
  selectedUniverseId: number | null = null;
  selectedStatus: string = 'DRAFT';
  @Input() statuses: { value: string, label: string }[] = [];
  @Input() universes: Universe[] = [];

  isSubmitting = false;
  error = '';
  creationSuccess = false;

  constructor(
    private campaignService: CampaignService,
    private authService: AuthService,
    private universeService: UniversesService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.universeService.getUniverses().subscribe({
      next: (data) => {
        this.universes = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des univers', err);
      }
    });
    this.campaignService.getStatuses().subscribe({
      next: (data) => {
        this.statuses = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des statuts', err);
      }
    });
    if (this.campaign) {
      this.title = this.campaign.title;
      this.description = this.campaign.description;
      this.selectedUniverseId = this.campaign.universeId ?? null;
      this.selectedStatus = this.campaign.status;
    }
  }

  onSubmit(): void {
    const userId = this.authService.getUserId();
    if (!userId) {
      this.error = 'Utilisateur non authentifié.';
      return;
    }

    const payload: Partial<Campaign> = {
      title: this.title,
      description: this.description,
      status: this.selectedStatus,
      universeId: this.selectedUniverseId || null
    };

    this.isSubmitting = true;

    if (this.campaign && this.campaign.id) {
      // Mode édition
      this.campaignService.patchCampaign(this.campaign.id, payload).subscribe({
        next: () => {
          this.creationSuccess = true;
          this.campaignUpdated.emit();
          window.location.reload();
        },
        error: (err) => {
          console.error(err);
          this.error = 'Erreur lors de la modification de la campagne.';
          this.isSubmitting = false;
        }
      });
    } else {
      // Mode création
      const newCampaign: Campaign = {
        ...payload,
        userId
      } as Campaign;

      this.campaignService.createCampaign(newCampaign).subscribe({
        next: () => {
          this.creationSuccess = true;
          window.location.reload();
        },
        error: (err) => {
          console.error(err);
          this.error = 'Erreur lors de la création de la campagne.';
          this.isSubmitting = false;
        }
      });
    }
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['campaign']?.currentValue) {
      this.title = this.campaign!.title;
      this.description = this.campaign!.description;
      this.selectedUniverseId = this.campaign!.universeId ?? null;
      this.selectedStatus = this.campaign!.status;
    }
  }
}
