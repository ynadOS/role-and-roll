import { Component, OnInit, AfterViewInit, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Campaign, CampaignService } from '../../../services/campaigns.service';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../../../shared/components/modal/modal/modal.component';
import { FormsModule } from '@angular/forms';
import { InvitationsService } from '../../../services/invitations.service';

@Component({
  selector: 'app-campaign-page',
  standalone: true,
  imports: [CommonModule, RouterLink, ModalComponent, FormsModule],
  templateUrl: './campaign-page.component.html',
  styleUrls: ['./campaign-page.component.css']
})
export class CampaignPageComponent implements OnInit, AfterViewInit {
  private route = inject(ActivatedRoute);
  private campaignService = inject(CampaignService);
  private invitationService = inject(InvitationsService);

  campaignId!: number;
  campaign?: Campaign;
  isModalOpen = false;
  inviteUsername = '';
  invitations: any[] = [];

  ngOnInit(): void {
    this.campaignId = +this.route.snapshot.paramMap.get('id')!;
    this.campaignService.getById(this.campaignId).subscribe((data: Campaign) => {
      this.campaign = data;
      this.invitationService.getInvitationsByCampaign(this.campaignId).subscribe({
        next: (invitations) => {
          this.invitations = invitations;
        },
        error: (err) => {
          console.error('Erreur lors de la récupération des invitations :', err);
        }
      });
    });
  }

  ngAfterViewInit(): void {
    this.route.fragment.subscribe(fragment => {
      if (fragment) {
        setTimeout(() => {
          const element = document.getElementById(fragment);
          if (element) {
            element.scrollIntoView({ behavior: 'smooth' });
          }
        }, 0);
      }
    });
  }
  invitePlayer(): void {
    if (!this.inviteUsername || !this.campaignId) return;

    const body = {
      userName: this.inviteUsername,
      campaignId: this.campaignId,
      // TODO: replace with real connected user ID logic
      createdById: 1
    };

    this.invitationService.sendInvitation(body).subscribe({
      next: (res) => {
        alert('Invitation envoyée avec succès à ' + this.inviteUsername);
        this.inviteUsername = '';
        this.isModalOpen = false;
      },
      error: (err) => {
        console.error('Erreur lors de l’envoi de l’invitation :', err);
      }
    });
  }
}