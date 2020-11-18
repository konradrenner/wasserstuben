import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map, shareReplay, take} from 'rxjs/operators';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {TenantService} from './tenant.service';
import {Tenant} from './tenant';
import {NavEntry} from './naventry';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit{
    
    navEntries: NavEntry[] = [{title:'Dashboard', link: '/skeleton', icon: 'dashboard'},
    {title:'Verrechnung', link: '/skeleton', icon: 'euro_symbol'},
    {title:'Verwaltung', link: '/skeleton', icon: 'fact_check'},
    {title:'Liegenschaften', link: '/real-estates', icon: 'home_work'},
    {title:'Adressen', link: '/skeleton', icon: 'contact_mail'}];
    
    tenantName = "AquaElit";
    actNavigation = "";
    
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private tenantService: TenantService) {}
  
  ngOnInit() {
      this.tenantName = this.tenantService.getTenant().name;
  }
  
  handleNavClick(navEntry: NavEntry){
      this.actNavigation = navEntry.title;
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
