import { Component, OnInit, Input, Output } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MstTableData } from '../mst-table-data';

@Component({
  selector: 'app-upper-section',
  templateUrl: './upper-section.component.html',
  styleUrls: ['./upper-section.component.css']
})
export class UpperSectionComponent implements OnInit{
  mstObj={
    'name':'',
    'value':0
  }
//  currenctYear = (new Date()).getFullYear
OptionList:string[]=[]
mstTable!:any;


option!:any
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService,private router:Router ){
 
 }

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
   
  }
  selectedOption!:true
  isDept:boolean=false
  isDesign:boolean=false 
  file!: File; 
  year!:string
  isComplete:boolean=false
  massage!:string

  onFileSubmit(){  
    const data = { name: 'John', age: 30 };
    
    // console.log(formValue.value.selection,formValue.value.option)
    this.fileService.upload(this.file,this.year).subscribe(res=>{
      console.log(res);
      this.massage="Completed!!"
      this.isComplete=true
      this.fileService.getMstTable().subscribe(res=>{
        this.mstTable = res;
        
        console.log(this.mstTable,this.OptionList)
      });
      // for(let i of this.OptionList)
      // {
      //   i.
      // }
    })
  
    console.log(this.file,this.year);
  }

  
  OnUpload(formValue: any){
    this.file = formValue.target.files[0];
    console.log(this.file )
  }


  OnSubmit(formValue:any){
    // console.log(formValue.value)

    this.router.navigate(['lowerSection'],{queryParams:formValue.value});
  }

  OnSelected(option:any){
    this.option = option.target.value
    if(this.option=='Department'){
      this.OptionList = this.mstTable.dept_list;
      
    }
    else{
      this.OptionList = this.mstTable.design_list;
    }
  }
  Onimport(){
    this.fileService.getMstTable().subscribe(res=>{
      this.mstTable = res;
      
      console.log(this.mstTable)
    });
  }
}
