// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
import { EChartsOption } from 'echarts';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
// import {  NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-lower-section',
  templateUrl: './lower-section.component.html',
  styleUrls: ['./lower-section.component.css']
})
export class LowerSectionComponent {
  // @Output() empData: Employee
  EmployeeList!: any
  isLoading!: boolean;
  data: any;
  DataList: any = [];
  Pieoption!: EChartsOption;
  Baroption!: EChartsOption;
  counter: any = 0;
  empData: any = [];
  colorGrid!: any
  colorValue = 0
  BarData: any = []
  weekData: any = []
  option!: any;
  value!: any;
  title!:string;
  ngOnInit() {
    // this.isLoading = true;
    // this.service.download().subscribe(res=>{
    //   this.EmployeeList = res;
    //   console.log(this.EmployeeList)
    //   this.isLoading=false;
    // })
    // this.route.queryParams.subscribe(params => {
    //   const option = params['option'];
    //   const value = params['value'];
    //   console.log(option,value);
    // });
    //  this.option = this.route.snapshot.paramMap.get('option');
    // this.value = this.route.snapshot.paramMap.get('value');
   

  }

  getData() {
    this.route.queryParams.subscribe(params => {
      // const option = params['option'];
      // const selection = params['selection'];
      // Use the parameter values as needed
      this.option = this.dataService.option
      this.value = this.dataService.selection
      this.isLoading = true;
      this.service.download(this.value, this.option).subscribe(res => {
        this.isLoading = false;
        this.EmployeeList = res;
        console.log(res);
        for (let i of this.EmployeeList) {
          console.log(i)
          const tempObj = {
            'name': '',
            'value': 0

          }
          // this.weekData.push(i.deptName)
          // this.BarData.push(i.salary/10000)
          // this.colorGrid = rgba(this.colorGrid, this.colorGrid+1, this.colorGrid, 0.5)'
          if (this.value == 'Department') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.deptName + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Department"
            this.weekData.push(i.deptName)
            
          }
          if (this.value == 'Designation') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.designation + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Designation"
            this.weekData.push(i.designation)
          }

          this.empData.push(tempObj)
          this.BarData.push(i.totalSalary/1000)
          this.PieLoading();
          this.BarLoading();
        }
      })
      console.log(this.option, this.value)
      // this.OnClick();
    });
    this.dataService.option = ''
    this.dataService.selection = ''
    this.empData = []
    this.BarData=[]
    this.weekData=[]
  }

  BarLoading() {
    this.Baroption = {
      xAxis: {
        type: 'category',
        data: this.weekData
        // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: this.BarData,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }
      ]
    };
  }


  PieLoading() {
    this.Pieoption = {

      tooltip: {
        trigger: 'item'
      },
      title: {
        text: this.title,
        left: "center",
        top: "top",
        textStyle: {
          fontSize: 30
        }
      },
      // legend: {
      //   orient: 'horizontal',
      //   left: 'left'
      // },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: this.empData
          ,
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


  constructor(private service: FileServiceService, private route: ActivatedRoute, private dataService: DataService) {

  }


  OnClick() {
    this.getData()
    // this.counter=0
    // // this.PieLoading();
    // this.isLoading = true;
    // this.service.download(this.option,this.value).subscribe(res=>{
    //   this.EmployeeList = res;
    //   // this.empData = res;
    //   console.log(this.EmployeeList)
    //   this.isLoading=false;

    //   // this.empData=[];
    //   for(let i of this.EmployeeList){
    //     console.log(i)
    //     const tempObj={
    //       'name':'',
    //       'value':0

    //     }
    //     // this.weekData.push(i.deptName)
    //     // this.BarData.push(i.salary/10000)
    //     // this.colorGrid = rgba(this.colorGrid, this.colorGrid+1, this.colorGrid, 0.5)'
    //     tempObj.value=i.totalSalary;
    //     tempObj.name=i.deptName;
    //     this.empData.push(tempObj)
    //    this.PieLoading();
    //     // this.BarLoading();
    //   } 
    //   console.log(this.empData)

    //   // this.data = [
    //   //   { 'name' : "dis_address", 'value' : this.EmployeeList[19].salary },
    //   //   {'name' : "resident", 'value' : this.EmployeeList[5].salary},
    //   //   {'name' : "salary", 'value' :   this.EmployeeList[2].salary }
    //   // ]
    // })


  }

  Prev() {
    this.counter -= 10
  }

  Next() {
    this.counter += 10
  }
  GetLog() {
    this.service.fetchData().subscribe(res => {
      this.DataList = res
      console.log(this.DataList)
    })
  }



  // colorScheme = {
  //   domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  // };


}



