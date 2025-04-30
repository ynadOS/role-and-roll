import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
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
  imports: [FormsModule, CommonModule, RouterModule]
})
export class CampaignFormComponent implements OnInit {
  @Input() campaign?: Campaign;
  @Output() formSubmitted = new EventEmitter<void>();
  @Output() campaignUpdated = new EventEmitter<void>();

  title = '';
  description = '';
  selectedUniverseId: number | null = null;
  selectedStatusId: number | null = null;
  @Input() statuses: Status[] = [];
  @Input() universes: Universe[] = [];

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
    if (this.campaign) {
      this.title = this.campaign.title;
      this.description = this.campaign.description;
      this.selectedStatusId = this.campaign.statusId;
      this.selectedUniverseId = this.campaign.universeId ?? null;
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
      statusId: this.selectedStatusId!,
      universeId: this.selectedUniverseId || null
    };

    this.isSubmitting = true;

    if (this.campaign && this.campaign.id) {
      // Mode édition
      this.campaignService.patchCampaign(this.campaign.id, payload).subscribe({
        next: () => {
          this.creationSuccess = true;
          this.campaignUpdated.emit();
          setTimeout(() => this.router.navigate(['/campaigns/overview']), 500);
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
          setTimeout(() => this.router.navigate(['/campaigns/overview']), 500);
        },
        error: (err) => {
          console.error(err);
          this.error = 'Erreur lors de la création de la campagne.';
          this.isSubmitting = false;
        }
      });
    }
  }
}
