import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { UploadDataComponent } from './upload-data/upload-data.component';
import { UpperSectionComponent } from './upper-section/upper-section.component';
import { LowerSectionComponent } from './lower-section/lower-section.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UploadDataComponent,
    UpperSectionComponent,
    LowerSectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
