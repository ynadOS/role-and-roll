import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

export interface Campaign {
  id?: number;
  title: string;
  description: string;
  userId: number;
  statusId: number;
  universeId?: number | null;
}

@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  private apiUrl = `${environment.apiUrl}/campaigns`;

  constructor(private http: HttpClient) {}

  createCampaign(campaign: Campaign): Observable<any> {
    return this.http.post(this.apiUrl, campaign);
  }

  getAll(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.apiUrl);
  }

  getById(id: number): Observable<Campaign> {
    return this.http.get<Campaign>(`${this.apiUrl}/${id}`);
  }

  update(id: number, campaign: Campaign): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, campaign);
  }

  patchCampaign(id: number, updateData: any) {
    return this.http.patch(`${this.apiUrl}/${id}`, updateData);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  getMyCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(`${this.apiUrl}/me`);
  }

  getStatuses(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/statuses`);
  }

  getUniverses(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/universes`);
  }
}