import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { map } from 'rxjs/operators';

export interface Campaign {
  id?: number;
  title: string;
  description: string;
  userId: number;
  status: string;
  universeName: string;
  statusLabel?: string;
  universeId?: number | null;
  createdAt?: string;   // 👈 ajoute ceci
  updatedAt?: string;   // 👈 et ceci

}

@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  private http = inject(HttpClient);

  private apiUrl = `${environment.apiUrl}/campaigns`;

  createCampaign(campaign: Campaign): Observable<any> {
    return this.http.post(this.apiUrl, campaign);
  }

  getAll(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.apiUrl);
  }

  getAllCampaignIds(): Observable<number[]> {
    return this.http.get<Campaign[]>(this.apiUrl)
      .pipe(
        map(campaigns => campaigns
          .filter(c => c.id !== undefined && c.id !== null)
          .map(c => c.id!))
      );
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
    return this.http.get<any[]>(`${this.apiUrl}/statuses`);
  }

  getUniverses(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/universes`);
  }
}