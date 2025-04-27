import { Component, OnInit, ElementRef, ViewChild, AfterViewChecked } from '@angular/core';
import { CampaignService } from '../../../services/campaigns.service';
import { AuthService } from '../../../services/auth.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OrderByPipe } from '../../../shared/pipes/order-by.pipe';

@Component({
  selector: 'app-campaign-overview',
  standalone: true,
  imports: [CommonModule, RouterModule, OrderByPipe],
  templateUrl: './campaign-overview.component.html',
  styleUrls: ['./campaign-overview.component.css']
})
export class CampaignOverviewComponent implements OnInit, AfterViewChecked {
  campaigns: any[] = [];
  isDeleteModalOpen = false; // Contrôle de la modale
  selectedCampaignId: number | null = null;
  @ViewChild('modalBackdrop') modalBackdrop!: ElementRef;


  constructor(
    private campaignService: CampaignService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.campaignService.getMyCampaigns().subscribe({
      next: (data) => {
        this.campaigns = data;
      },
      error: (err) => {
        console.error('Erreur chargement des campagnes :', err);
      }
    });
  }

  openDeleteModal(campaignId: number): void {
    this.isDeleteModalOpen = true;
    this.selectedCampaignId = campaignId;
  }

  closeDeleteModal(): void {
    this.isDeleteModalOpen = false;
    this.selectedCampaignId = null;
  }

  deleteCampaign(): void {
    if (this.selectedCampaignId !== null) {
      this.campaignService.delete(this.selectedCampaignId).subscribe({
        next: () => {
          // Met à jour la liste localement après la suppression
          this.campaigns = this.campaigns.filter(campaign => campaign.id !== this.selectedCampaignId);
          this.closeDeleteModal(); // Ferme la modale après suppression
        },
        error: (err) => {
          console.error('Erreur lors de la suppression de la campagne :', err);
        }
      });
    }
  }
  
  ngAfterViewChecked(): void {
    if (this.isDeleteModalOpen && this.modalBackdrop) {
      this.modalBackdrop.nativeElement.focus();
    }
  }
}