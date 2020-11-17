import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DefaultComponent, SkeletonComponent} from './app.component'
import {RealEstatesComponent} from './real-estates/real-estates.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: DefaultComponent},
  {path: 'skeleton', component: SkeletonComponent},
  {path: 'real-estates', component: RealEstatesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
