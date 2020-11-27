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

  getRealEstates(search: string, limit: number, page: number, sort: string, order: string): Observable<RealEstateList>{
      return this.http.get<RealEstateList>('/api/v1/realestates?search='+search+'&limit='+limit+'&page='+page+'sort='+'&order='+order);
  }

  getRealEstate(cadastralTownshipNumber: number, estateId: string, depositNumber: number): Observable<RealEstate>{
    return this.http.get<RealEstate>('/api/v1/realestates/'+cadastralTownshipNumber+'-'+estateId+'-'+depositNumber);
}
}
