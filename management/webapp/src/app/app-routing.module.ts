import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SkeletonComponent} from './app.component'
import {RealEstatesComponent} from './real-estates/real-estates.component';
import {RealEstatesDetailComponent} from './real-estates-detail/real-estates-detail.component';

const routes: Routes = [
  {path: 'skeleton', component: SkeletonComponent},
  {path: 'real-estates', component: RealEstatesComponent},
  {path: 'real-estates/:id', component: RealEstatesDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
