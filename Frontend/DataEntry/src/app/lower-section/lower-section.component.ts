// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
import {  NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-lower-section',
  templateUrl: './lower-section.component.html',
  // template:`<button (click)="showSpinner()">Show Spinner</button>`,
  styleUrls: ['./lower-section.component.css']
})
export class LowerSectionComponent implements OnInit{
  EmployeeList!:any
  isLoading!:boolean
  ngOnInit(): void {
    // this.isLoading = true;
    // this.service.download().subscribe(res=>{
    //   this.EmployeeList = res;
    //   console.log(this.EmployeeList)
    //   this.isLoading=false;
    // })
  }
  
  constructor(private service:FileServiceService,private spinner:NgxSpinnerService){
  }

  OnClick(){
    this.isLoading = true;
    this.service.download().subscribe(res=>{
      this.EmployeeList = res;
      console.log(this.EmployeeList)
      this.isLoading=false;
    })

  }
 

  }



