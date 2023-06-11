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
import  { saveAs } from 'file-saver';
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
  logMsg:string="Get-Log"
  value!: any;
  title!:string;
  isTrue!:boolean
  @Input() formValue:FormGroup;


  
  constructor(private service: FileServiceService, private route: Router, private router: ActivatedRoute,private formBuilder:FormBuilder) {
    this.formValue=this.formBuilder.group({})
  }

ngOnInit():void{
  this.isNull=false
  console.log(this.isNull)
  this.formValue.valueChanges.subscribe((res)=>{
    if(res.selectedOp[0]!='' && res.selection[0]!=''){
      this.option = res.selection
    this.value = res.selectedOp
    console.log(res)
   console.log(this.option,this.value)
   console.log(this.isNull)
   this.isNull = true
    }
   
  })
}

  getData() {
   
    
      this.option = this.formValue.value.selection
      this.value = this.formValue.value.selectedOp
      console.log(this.option,this.value)
        
      this.isLoading = true;
      this.service.download(this.option, this.value).subscribe(res => {
       
        // this.isNull=true
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
            this.weekData.push(this.getInitials(i.designation))
            
          }
          if (this.option == 'Designation') {
            tempObj.value = i.totalSalary;
            tempObj.name = i.designation + ' ' + (i.totalSalary / 1000) + 'K';
            this.title = "Designation"
            this.weekData.push(this.getInitials(i.designation))
          }
          // console.log(this.empData)
          this.empData.push(tempObj)
          this.BarData.push(i.totalSalary/100000)
        }
        console.log(this.empData,this.BarData,this.weekData)

          this.PieLoading();
          this.BarLoading();
          this.option = ''
          this.value = ''
          this.empData = []
          this.BarData=[]
          this.weekData=[]
        
        }
        this.isLoading=false
        this.isNull=false;
    });
  
  }
   getInitials(inputString: string): string {
    const words = inputString.split(" ");
    const initials = words.map(word => word.charAt(0).toUpperCase());
    return initials.join("");
  }
  BarLoading() {
    this.Baroption = {
      xAxis: {
        type: 'category',
        data: this.weekData,
        // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        nameLocation: "end",
    name: this.option
      },
      yAxis: {
        type: 'value',
        nameLocation: "end",
    name: "Salary per 1k"
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

      // tooltip: {
      //   trigger: 'item'
      // },
      title: {
        text: this.title,
        left: "center",
        top: "top",
        textStyle: {
          fontSize: 15
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
    if(this.isLog){
      this.logMsg="Get-Log"
      this.isLog=false
    }
    else{
    // 
    this.service.fetchData().subscribe(res => {
      this.DataList = res
      console.log(this.DataList)
      this.isLog=true 
      this.logMsg="Close-Log"
    })}
  }

  downdLog(){
    this.service.downloadLog().subscribe(
      (response: string) => {
        const fileName = 'file.txt'; // Specify the desired file name
        const blob = new Blob([response], { type: 'text/plain' });
        saveAs(blob, fileName);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  // colorScheme = {
  //   domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  // };


}



