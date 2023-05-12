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
@Output() formData = new EventEmitter<FormGroup>();
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
    this.selectionForm.valueChanges.subscribe(res=>{
     
      // console.log(res)
    })
    this.OnSelected();
  }
  selectedOption!:true
  isDept:boolean=false
  isDesign:boolean=false 
  file!: File; 
  year!:string
  isComplete:boolean=false
  massage!:string

  onFileSubmit(){  
    
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


  OnSubmit(){
    // console.log(inputForm.value)
    // this.dataService.option = formValue.value.option
    // this.dataService.selection = formValue.value.selection
    // this.router.navigate(['lowerSection']);
  }

  OnSelected(){
    // this.option = option.target.value
    // if(this.option=='Department'){
    //   this.OptionList = this.mstTable.dept_list;
    // }
    // else if(this.option=='Designation'){
    //   this.OptionList = this.mstTable.design_list;
    // }
  

  this.selectionForm.valueChanges.subscribe(value => {
    if (value.selection === 'Department') {
      console.log(value.selection)
      this.OptionList = this.mstTable.dept_list;
      // this.selectionForm.setValue();
      
    } else if (value.selection === 'Designation') {
      console.log(value.selection)
      this.OptionList = this.mstTable.design_list;
      // this.selectionForm.setValue({selection: this.selectionForm.value.selection,
      //   selectedOp: ['']});
      
    } 
    if(this.selectionForm.value.selectedOp!='' && this.selectionForm.value.selection!='')
    {
      this.formData.emit(this.selectionForm)
    }
    // console.log(value)
    
  })
}

  Onimport(){
    this.fileService.getMstTable().subscribe(res=>{
      this.mstTable = res;
      console.log(this.mstTable)
    });
  }
}
