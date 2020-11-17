import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {take} from 'rxjs/operators';
import {RealEstate, RealEstateId} from './realestate';

@Injectable({
  providedIn: 'root'
})
export class RealEstatesService {
    
  constructor(private http: HttpClient) { }

  getRealEstates(): Observable<RealEstate[]>{
      return this.http.get<RealEstate[]>('/api/v1/realestates')
  }
}
