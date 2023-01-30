import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalComponent } from './animal/animal.component';
import { AnimalOverviewComponent } from './animal/animalOverview.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/find/:uuid', component: AnimalComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
