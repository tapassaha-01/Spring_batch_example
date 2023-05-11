import { Component, OnInit, Input, Output } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { ActivatedRoute, Data, Router } from '@angular/router';
import { MstTableData } from '../mst-table-data';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup } from '@angular/forms';

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
selectionForm:FormGroup;

option!:any
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService,private router:Router,private dataService:DataService,private fromBuilder:FormBuilder  ){
      this.selectionForm=this.fromBuilder.group({
        firstName: [''],
        lastName: [''],
        address: this.fb.group({
          street: [''],
          city: [''],
          state: [''],
          zip: ['']
        }),
      });
 }

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
    this.fileService.getMstTable().subscribe(res=>{
      this.mstTable = res;
      
      console.log(this.mstTable,this.OptionList)
    });
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


  // OnSubmit(){
  //   console.log(inputForm.value)
  //   this.dataService.option = formValue.value.option
  //   this.dataService.selection = formValue.value.selection
  //   this.router.navigate(['lowerSection']);
  // }

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
