import { Component, OnInit, Input, Output } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upper-section',
  templateUrl: './upper-section.component.html',
  styleUrls: ['./upper-section.component.css']
})
export class UpperSectionComponent implements OnInit{

 currenctYear = (new Date()).getFullYear
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService,private router:Router ){}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  file!: File; 
  year!:string
  isComplete:boolean=false
  massage!:string
 
  onSubmit(){
    this.router.navigate(['lowerSection']);
    
    this.fileService.upload(this.file,this.year).subscribe(res=>{
      console.log(res);
      this.massage="Completed!!"
      this.isComplete=true
     
    })
  
    console.log(this.file,this.year);
  }
  OnUpload(event: any){
    this.file = event.target.files[0];

  }

  OnSelected(year:any){
    this.year = year.target.value
    
  }
}
