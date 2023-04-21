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
// import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
// import { NgxSpinnerModule } from "ngx-spinner";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PieChartComponent } from './pie-chart/pie-chart.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UploadDataComponent,
    UpperSectionComponent,
    LowerSectionComponent,
    PieChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    NgxChartsModule
    // NgxChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
