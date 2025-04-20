import { Injectable } from '@angular/core';
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

  constructor(private http: HttpClient) { }

  getUniverses(): Observable<Universe[]> {
    return this.http.get<Universe[]>(`${environment.apiUrl}/universes`);
  }
}
