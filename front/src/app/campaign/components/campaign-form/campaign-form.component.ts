import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StatusService } from '../../../services/status.service';

@Component({
  selector: 'app-campaign-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './campaign-form.component.html',
  styleUrls: ['./campaign-form.component.css'],
})
export class CampaignFormComponent implements OnInit {
  statuses: any[] = [];
  selectedStatusId: string = '';

  constructor(private statusService: StatusService) {}

  ngOnInit(): void {
    this.statusService.getStatuses().subscribe((data) => {
      this.statuses = data;
    });
  }
}
