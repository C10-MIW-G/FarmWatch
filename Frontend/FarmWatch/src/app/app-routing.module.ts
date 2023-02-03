import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './animal-overview/animal-overview.component';
import { UpdateAnimalComponent } from './update-animal/update-animal.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/:id', component: AnimalDetailComponent},
  { path: 'animal/update/:id', component: UpdateAnimalComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
