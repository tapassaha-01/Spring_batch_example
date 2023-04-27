// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
import { EChartsOption } from 'echarts';
// import {  NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-lower-section',
  templateUrl: './lower-section.component.html',
  styleUrls: ['./lower-section.component.css']
})
export class LowerSectionComponent {
  // @Output() empData: Employee
  EmployeeList!:any
  isLoading!:boolean;
  data:any;
  DataList:any=[];
  option!:EChartsOption;
  counter:any=0;
  ngOnInit(){
    // this.isLoading = true;
    // this.service.download().subscribe(res=>{
    //   this.EmployeeList = res;
    //   console.log(this.EmployeeList)
    //   this.isLoading=false;
    // })
    
    
    
  }
  
  PieLoading(){
    this.option = {
     
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'horizontal',
        left: 'left'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: 'Search Engine' },
            { value: 735, name: 'Direct' },
            { value: 580, name: 'Email' },
            { value: 484, name: 'Union Ads' },
            { value: 300, name: 'Video Ads' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
  }


  constructor(private service:FileServiceService){
    
  }


  OnClick(){
    this.counter=0
    // this.PieLoading();
    this.isLoading = true;
    this.service.download().subscribe(res=>{
      this.EmployeeList = res;
      // this.empData = res;
      console.log(this.EmployeeList)
      this.isLoading=false;
      this.PieLoading();
      // this.data = [
      //   { 'name' : "dis_address", 'value' : this.EmployeeList[19].salary },
      //   {'name' : "resident", 'value' : this.EmployeeList[5].salary},
      //   {'name' : "salary", 'value' :   this.EmployeeList[2].salary }
      // ]
    })

    
  }
  
  Prev(){
    this.counter-=10
  }

  Next(){
    this.counter+=10
  }
  GetLog(){
    this.service.fetchData().subscribe(res=>{
     this.DataList=res
      console.log(this.DataList)
    })
  }
  


  // colorScheme = {
  //   domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  // };
 

  }



