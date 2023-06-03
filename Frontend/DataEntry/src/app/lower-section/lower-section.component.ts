// import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit ,Input,OnChanges,SimpleChanges } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';
import { EChartsOption } from 'echarts';
import { ActivatedRoute, NavigationEnd, NavigationStart, ParamMap, Router } from '@angular/router';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup } from '@angular/forms';
// import {  NgxSpinnerService } from 'ngx-spinner';
import { filter,switchMap  } from 'rxjs/operators';
// import { Observable } from 'rxjs';

@Component({
  selector: 'app-lower-section',
  templateUrl: './lower-section.component.html',
  styleUrls: ['./lower-section.component.css']
})
export class LowerSectionComponent {
  // @Output() empData: Employee
  EmployeeList!: any
  errorMassage!:any
  isLoading!: boolean;
  isNull!:boolean
  isLog!:boolean;
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
  isTrue!:boolean
  @Input() formValue:FormGroup;


  
  constructor(private service: FileServiceService, private route: Router, private router: ActivatedRoute,private formBuilder:FormBuilder) {
    this.formValue=this.formBuilder.group({})
  }

ngOnInit():void{
  this.formValue.valueChanges.subscribe((res)=>{
    if(this.formValue.value.selectedOp!='' && this.formValue.value.selection!=''){
      this.option = res.selection
    this.value = res.selectedOp
   console.log(this.option,this.value)
   this.isNull = true
    }
    
   
  })
 
  
   
}


  

  getData() {
   
    
      this.option = this.formValue.value.selection
      this.value = this.formValue.value.selectedOp
      console.log(this.option,this.value)
        this.isNull=true
      this.isLoading = true;
      this.service.download(this.option, this.value).subscribe(res => {
        this.isLoading=false
        console.log("inside service.downlaod")
        this.EmployeeList = res;
        this.isTrue = (this.EmployeeList[0].totalDesignation!=0)?true:false
        // if(this.EmployeeList.colu)
        console.log(this.EmployeeList);
        if(this.EmployeeList!=null){
        for (let i of this.EmployeeList) {
          // console.log(i)
          const tempObj = {
            'name': '',
            'value': 0

          }
          if (this.option == 'Department') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.designation + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Department"
            this.weekData.push(i.designation)
            
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
        }
        // console.log(this.empData,this.BarData,this.weekData)
          this.PieLoading();
          this.BarLoading();
          this.option = ''
          this.value = ''
          this.empData = []
          this.BarData=[]
          this.weekData=[]
        
        }
    });
  
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




  OnClick() {
    this.getData()
    console.log(this.formValue.value);
  }

  Prev() {
    this.counter -= 10
  }

  Next() {
    this.counter += 10
  }
  GetLog() {
    this.isLog=false
    this.service.fetchData().subscribe(res => {
      this.DataList = res
      console.log(this.DataList)
      this.isLog=true 
    })
  }



  // colorScheme = {
  //   domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  // };


}



