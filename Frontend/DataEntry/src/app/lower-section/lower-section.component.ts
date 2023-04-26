// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
// import {  NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-lower-section',
  templateUrl: './lower-section.component.html',
  // template:`<button (click)="showSpinner()">Show Spinner</button>`,
  styleUrls: ['./lower-section.component.css']
})
export class LowerSectionComponent implements OnInit{
  // @Output() empData: Employee
  EmployeeList!:any
  isLoading!:boolean;
  data:any;
  DataList!:any
  ngOnInit(): void {
    // this.isLoading = true;
    // this.service.download().subscribe(res=>{
    //   this.EmployeeList = res;
    //   console.log(this.EmployeeList)
    //   this.isLoading=false;
    // })
  }
  
  constructor(private service:FileServiceService){
  }

  OnClick(){
    this.isLoading = true;
    this.service.download().subscribe(res=>{
      this.EmployeeList = res;
      // this.empData = res;
      console.log(this.EmployeeList)
      this.isLoading=false;

      this.data = [
        { 'name' : "dis_address", 'value' : this.EmployeeList[19].salary },
        {'name' : "resident", 'value' : this.EmployeeList[5].salary},
        {'name' : "salary", 'value' :   this.EmployeeList[2].salary }
      ]
    })
  }
  GetLog(){
    this.service.fetchData().subscribe(res=>{
     this.DataList=res
      console.log(this.DataList)
    })
  }
  

  colorScheme = {
    domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  };
 

  }



