import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EChartsComponent } from './echarts.component';
import * as echarts from 'echarts';

@NgModule({
  declarations: [EChartsComponent],
  imports: [CommonModule],
  providers: [
    {
      provide: 'echarts',
      useValue: echarts
    }
  ]
})
export class EchartsModule {}
