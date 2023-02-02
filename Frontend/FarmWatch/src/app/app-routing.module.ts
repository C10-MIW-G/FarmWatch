import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './animal-overview/animal-overview.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/:id', component: AnimalDetailComponent},
  { path: 'add-animal', component: AddAnimalComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
