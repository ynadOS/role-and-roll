import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Campaign, CampaignService } from '../../../services/campaigns.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-campaign-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './campaign-page.component.html',
  styleUrls: ['./campaign-page.component.css']
})
export class CampaignPageComponent implements OnInit {
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
}