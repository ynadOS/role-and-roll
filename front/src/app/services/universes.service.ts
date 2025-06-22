import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

interface Universe {
  id: number;
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class UniversesService {
  private http = inject(HttpClient);


  getUniverses(): Observable<Universe[]> {
    return this.http.get<Universe[]>(`${environment.apiUrl}/universes`);
  }
}
