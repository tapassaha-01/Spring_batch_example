// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit ,Input} from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
import { EChartsOption } from 'echarts';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  @Input() formValue:FormGroup;
  ngOnInit() {
    
  }

  getData() {
   
      this.option = this.formValue.value.selection
      this.value = this.formValue.value.selectedOp
      this.isLoading = true;
      this.service.download(this.option, this.value).subscribe(res => {
        this.isLoading = false;
        this.EmployeeList = res;
        console.log(this.EmployeeList);
        for (let i of this.EmployeeList) {
          // console.log(i)
          const tempObj = {
            'name': '',
            'value': 0

          }
          // this.weekData.push(i.deptName)
          // this.BarData.push(i.salary/10000)
          // this.colorGrid = rgba(this.colorGrid, this.colorGrid+1, this.colorGrid, 0.5)'
          if (this.option == 'Department') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.deptName + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Department"
            this.weekData.push(i.deptName)
            
          }
          if (this.option == 'Designation') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.designation + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Designation"
            this.weekData.push(i.designation)
          }
          // console.log(this.empData)
          this.empData.push(tempObj)
          this.BarData.push(i.totalSalary/1000)
          this.PieLoading();
          this.BarLoading();
        }
     
      // console.log(this.option, this.value)
      // this.OnClick();
    });
    // this.dataService.option = ''
    // this.dataService.selection = ''
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


  constructor(private service: FileServiceService, private route: ActivatedRoute, private dataService: DataService,private formBuilder:FormBuilder) {
    this.formValue=this.formBuilder.group({})
  }


  OnClick() {
    this.getData()
    console.log(this.formValue.value)
   

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



