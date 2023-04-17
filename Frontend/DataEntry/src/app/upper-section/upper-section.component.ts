import { Component, OnInit } from '@angular/core';
import { FileServiceService } from '../file-service.service';

@Component({
  selector: 'app-upper-section',
  templateUrl: './upper-section.component.html',
  styleUrls: ['./upper-section.component.css']
})
export class UpperSectionComponent implements OnInit{
 currenctYear = (new Date()).getFullYear
  YearList=[2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023]
 constructor(private fileService:FileServiceService){}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  file!: File; 
  year!:string
  onSubmit(){
    this.fileService.upload(this.file,this.year).subscribe(res=>{
      console.log(res);
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
