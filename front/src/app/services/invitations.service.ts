import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InvitationsService {
  private http = inject(HttpClient);


  sendInvitation(data: { userName: string; campaignId: number; createdById: number }): Observable<any> {
    return this.http.post(`${environment.apiUrl}/invitations`, data);
  }

  getInvitationsByCampaign(campaignId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/invitations/campaign/${campaignId}`);
  }
}
