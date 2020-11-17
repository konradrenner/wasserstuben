import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { TenantService } from './tenant.service';
import { AppComponent } from './app.component';
import { SkeletonComponent} from './app.component'
import { HttpClientModule } from '@angular/common/http';
import { RealEstatesComponent } from './real-estates/real-estates.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';

export function startupTenantFactory(tenantService: TenantService): Function {
    return () => tenantService.load();
}

@NgModule({
  declarations: [
    AppComponent,
    SkeletonComponent,
    RealEstatesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [
    TenantService,
        {
            provide: APP_INITIALIZER,
            useFactory: startupTenantFactory,
            deps: [TenantService],
            multi: true
        }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
