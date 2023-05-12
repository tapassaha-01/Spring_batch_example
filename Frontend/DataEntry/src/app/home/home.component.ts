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
  constructor(private formBuilder:FormBuilder){
    this.selectionForm=this.formBuilder.group({})
  }
 
  ngOnInit(): void {
    // this.selectionForm=this.fromBuilder.group({
    //   selection: new FormControl(['']), //get the choice between designation and department
    //   selectedOp: new FormControl(['']) //get the value of optionlist
    // });
    
    // console.log(this.selectionForm)
    // this.selectionForm.valueChanges.subscribe(res=>{
    //   console.log(res)
    // })
  }
  selectedOption(data:FormGroup)
  {
    this.selectionForm=data
    console.log(data.value);
  }
}
