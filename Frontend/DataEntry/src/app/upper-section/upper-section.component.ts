import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { ActivatedRoute, Data, Router } from '@angular/router';
import { MstTableData } from '../mst-table-data';
import { DataService } from '../data.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

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
dynamicStyle: any = {
  'color': 'Black',
};
isgetData!:boolean;
selectionForm:FormGroup;
isNull!:boolean
isLoading!:boolean
@Output() formData = new EventEmitter<FormGroup>();
@Output() isActive = new EventEmitter<boolean>();
@Output() isProcess = new EventEmitter<boolean>();
option!:any
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService,private router:Router,private dataService:DataService,private fromBuilder:FormBuilder  ){
      this.selectionForm=this.fromBuilder.group({
        selection: new FormControl(['',Validators.required]),
        selectedOp: new FormControl(['',Validators.required]),
        Year:new FormControl(['']),
        uploadedFile:new FormControl([''])
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
  isSelectedYear :boolean=false
  isDesign:boolean=false 
  files!:FileList
  year!:string
  isComplete:boolean=false
  massage!:string

  onFileSubmit(){  
    
    
    // this.selectionForm.valueChanges.subscribe(val=>{
    //   this.isSelectedYear = true
    //   this.year = val.Year
    //   console.log(this.year)
    // })
    this.year = this.selectionForm.value.Year
    // this.files = 
    if(this.year!=""){this.isLoading=true
      this.isgetData=false
      this.isProcess.emit(this.isLoading)
    this.fileService.upload(this.files,this.year).subscribe(res=>{
      console.log(res);
      this.massage=res;
      if(this.massage=="COMPLETED"){
        this.dynamicStyle = {
          'color':'white '
        }
      }
      else{
        this.dynamicStyle = {
          'color':'Red'
        }
      }
     
      this.isLoading=false
      this.isgetData=true
      this.isProcess.emit(this.isLoading)
    })}
    else{this.isSelectedYear=false
      alert("select year first!!")
    }
    console.log(this.files,this.year);
  }

  
  OnUpload(formValue: any){
    // this.file = formValue.target.files[0];
     this.files = formValue.target.files
    for (let i = 0; i < this.files.length; i++) {
      const file: File = this.files[i];
      console.log(file.name); // Access file name
      console.log(file.size); // Access file size
      // Perform additional operations with each file as needed
    }
    console.log(this.files )
  }


    OnSubmit(){
      this.formData.emit(this.selectionForm)
  this.selectionForm.valueChanges.subscribe(value => {
    // this.router.navigate([''])
    
    if (value.selection === 'Department') {
      
      this.OptionList = this.mstTable.dept_list;
      this.OptionList.sort()
      
    } else if (value.selection === 'Designation') {
      
      this.OptionList = this.mstTable.design_list;
      this.OptionList.sort()
      
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
