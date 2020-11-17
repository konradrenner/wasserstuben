import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import { RealEstatesService } from '../real-estates.service';
import {RealEstate} from '../realestate';

@Component({
  selector: 'app-real-estates',
  templateUrl: './real-estates.component.html',
  styleUrls: ['./real-estates.component.sass']
})
export class RealEstatesComponent implements OnInit {
    
  realestates: RealEstate[] = [];

  constructor(private realEstatesService: RealEstatesService) { }

  ngOnInit(): void {
      this.getRealEstates();
  }

  getRealEstates(): void {
      this.realEstatesService.getRealEstates()
      .subscribe(estates => this.realestates = estates);
  }
}
