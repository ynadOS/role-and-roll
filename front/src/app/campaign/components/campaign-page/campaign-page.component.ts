import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Campaign, CampaignService } from '../../../services/campaigns.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-campaign-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './campaign-page.component.html',
  styleUrls: ['./campaign-page.component.css']
})
export class CampaignPageComponent implements OnInit, AfterViewInit {
  campaignId!: number;
  campaign?: Campaign;
  showAddMenu = false;

  constructor(
    private route: ActivatedRoute,
    private campaignService: CampaignService
  ) {}

  ngOnInit(): void {
    this.campaignId = +this.route.snapshot.paramMap.get('id')!;
    this.campaignService.getById(this.campaignId).subscribe((data: Campaign) => {
      this.campaign = data;
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

  handleAdd(type: string): void {
    this.showAddMenu = false;
    switch (type) {
      case 'inviter':
        console.log('Ajouter un joueur');
        // ouvrir modal ou exécuter logique pour inviter
        break;
      case 'session':
        console.log('Ajouter une session');
        // logique pour ajouter une session
        break;
      case 'pnj':
        console.log('Ajouter un PNJ');
        // logique pour ajouter un PNJ
        break;
    }
  }
}