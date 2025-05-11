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
}