import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map, shareReplay, take} from 'rxjs/operators';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {TenantService} from './tenant.service';
import {Tenant} from './tenant';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit{
    
    tenantName = "Watermanager";
    
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private tenantService: TenantService) {}
  
  ngOnInit() {
      this.tenantName = this.tenantService.getTenant().name;
  }
}

@Component({
  selector: 'app-skeleton',
  template: `
    OMG you are in the <b>SKELETON</b> component. <a [routerLink]="['/']">Default</a>
  `,
  styles: []
})
export class SkeletonComponent {
}
