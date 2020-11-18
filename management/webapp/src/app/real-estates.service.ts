import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {take} from 'rxjs/operators';
import {RealEstateList, RealEstate, RealEstateId} from './realestate';

@Injectable({
  providedIn: 'root'
})
export class RealEstatesService {
    
  constructor(private http: HttpClient) { }

  getRealEstates(limit: number, page: number, sort: string, order: string): Observable<RealEstateList>{
      return this.http.get<RealEstateList>('/api/v1/realestates?limit='+limit+'&page='+page+'sort='+'&order='+order);
  }
}
