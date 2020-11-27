import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RealEstatesService } from '../real-estates.service';
import { RealEstate } from '../realestate';

@Component({
  selector: 'app-real-estates-detail',
  templateUrl: './real-estates-detail.component.html',
  styleUrls: ['./real-estates-detail.component.css']
})
export class RealEstatesDetailComponent implements OnInit {

  cadastralTownshipNumber !: number;
  estateId !: string;
  depositNumber !: number;

  realEstate !: RealEstate;

  isLoadingResults = true;

  constructor(private realEstatesService: RealEstatesService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      let id = params['id'];
      let splitted = id.split("-");
      this.cadastralTownshipNumber = splitted[0];
      this.estateId = splitted[1];
      this.depositNumber = splitted[2];
    });

    this.realEstatesService.getRealEstate(this.cadastralTownshipNumber, this.estateId, this.depositNumber)
    .subscribe(estate => this.realEstate = estate);
  }

}
