import {HttpClient} from '@angular/common/http';
import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { RealEstatesService } from '../real-estates.service';
import {RealEstate, Owner} from '../realestate';

@Component({
  selector: 'app-real-estates',
  templateUrl: './real-estates.component.html',
  styleUrls: ['./real-estates.component.css']
})
export class RealEstatesComponent implements AfterViewInit {
  displayedColumns: string[] = ['cadastralTownshipNumber', 'estateId', 'depositNumber', 'owner', 'fittings'];
  
  resultsLength = 0;
  isLoadingResults = true;
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
    
  realestates: RealEstate[] = [];

  constructor(private realEstatesService: RealEstatesService) { }

  ngAfterViewInit() {
      // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.realEstatesService!.getRealEstates(this.paginator.pageSize, this.paginator.pageIndex, this.sort.active, this.sort.direction);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalNumber;

          return data.realestates;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.realestates = data);
  }
  
  printOwners(owner: Owner[]): string{
      var ret:string = "";
      owner.forEach(o => ret.concat(o.lastname).concat(' ').concat(o.firstname).concat(', '));
      ret.substr(0, ret.length-2);
      return ret;
  }
  
}
