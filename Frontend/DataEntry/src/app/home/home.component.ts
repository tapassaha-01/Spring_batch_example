import { Component, Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  // @Input() selectionForm!:FormGroup
  selectionForm:FormGroup;
  isNull!:boolean;
  constructor(private formBuilder:FormBuilder){
    this.selectionForm=this.formBuilder.group({})
  }
 
  ngOnInit(): void {
    
  }
  selectedOption(data:FormGroup)
  {
    // this.isNull=true
    this.selectionForm=data
    console.log(data.value);
  }
  OnSubmit(){
  

  }
  
}
