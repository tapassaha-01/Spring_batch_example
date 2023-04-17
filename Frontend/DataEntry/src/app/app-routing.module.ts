import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UploadDataComponent } from './upload-data/upload-data.component';
import { UpperSectionComponent } from './upper-section/upper-section.component';
import { LowerSectionComponent } from './lower-section/lower-section.component';

const routes: Routes = [
 {
  path:'',
  component:HomeComponent
 },
 {
  path:'uploadData',
  component:UploadDataComponent
 },
 {
  path:'upperSection',
  component:UpperSectionComponent
 },
 {
  path:'lowerSection',
  component:LowerSectionComponent
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
