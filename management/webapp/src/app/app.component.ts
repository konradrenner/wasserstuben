import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  template: `
    <div style="text-align:center">
      <img src="/assets/quarkus_logo_horizontal_rgb_1280px_reverse.svg"/>
    </div>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
  title = 'webapp';
}

@Component({
  selector: 'app-default',
  template: `
    In <b>default</b> component. 
    <a [routerLink]="['/skeleton']">Other</a> | 
    <a [routerLink]="['/real-estates']">Real Estates</a> 
  `,
  styles: []
})
export class DefaultComponent {
  externalUrl = '/servlet/make-external-call';

  constructor() {
    if (window.location.port === "4200") {
      this.externalUrl = "http://localhost:8080" + this.externalUrl;
    }
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
