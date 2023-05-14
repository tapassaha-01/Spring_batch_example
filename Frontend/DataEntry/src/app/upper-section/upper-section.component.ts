import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { ActivatedRoute, Data, Router } from '@angular/router';
import { MstTableData } from '../mst-table-data';
import { DataService } from '../data.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

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
isNull!:boolean
@Output() formData = new EventEmitter<FormGroup>();
@Output() isActive = new EventEmitter<boolean>();
option!:any
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService,private router:Router,private dataService:DataService,private fromBuilder:FormBuilder  ){
      this.selectionForm=this.fromBuilder.group({
        selection: new FormControl(['']),
        selectedOp: new FormControl(['']),
      });
      
 }

  ngOnInit(): void {
    // this is for loading the master table for the first time
    this.fileService.getMstTable().subscribe(res=>{
      this.mstTable = res;
      // this.OptionList=this.mstTable.design_list
      console.log(this.mstTable,this.OptionList)
    });
    // this.selectionForm.valueChanges.subscribe(res=>{
     
    //   // console.log(res)
    // })
    this.OnSubmit();
  }
  selectedOption!:true
  isDept:boolean=false
  isDesign:boolean=false 
  file!: File; 
  year!:string
  isComplete:boolean=false
  massage!:string

  onFileSubmit(){  
    
    this.fileService.upload(this.file,this.year).subscribe(res=>{
      console.log(res);
      this.massage="Completed!!"
      this.isComplete=true
    })
    
    console.log(this.file,this.year);
  }

  
  OnUpload(formValue: any){
    this.file = formValue.target.files[0];
    console.log(this.file )
  }


    OnSubmit(){
      this.formData.emit(this.selectionForm)
  this.selectionForm.valueChanges.subscribe(value => {
    this.router.navigate([''])
    if (value.selection === 'Department') {
      this.OptionList = this.mstTable.dept_list;
    } else if (value.selection === 'Designation') {
      this.OptionList = this.mstTable.design_list;
    } 
    
  })
}

  
  Onimport(){
    this.fileService.getMstTable().subscribe(res=>{
      this.mstTable = res;
      console.log(this.mstTable)
    });
  }
}
