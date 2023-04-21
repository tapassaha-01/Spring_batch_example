import { Component, Input } from '@angular/core';
import { FileServiceService } from '../file-service.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent {
  @Input() empData!: any
  name = 'Angular';
  Emp!:Employee;
  constructor(private service:FileServiceService){
    // console.log(this.empData[0].resident)
  }
 
  
  data = [
    { 'name' : "dis_address", 'value' : 765 },
    {'name' : "resident", 'value' : 35},
    // {'name' : "salary", 'value' :   this.empData[0].salary }
  ]

  colorScheme = {
    domain: ['#08DDC1', '#FFDC1B', '#FF5E3A']
  };
}
