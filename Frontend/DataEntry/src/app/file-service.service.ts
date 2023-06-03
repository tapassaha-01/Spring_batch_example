import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { MstTableData } from './mst-table-data';

@Injectable({
  providedIn: 'root'
})
export class FileServiceService {

  baseApiUrl = "http://localhost:8080/add";
  constructor(private http:HttpClient) {}


	fetchData(){
    return this.http.get(this.baseApiUrl+'/readLog');
   }


    upload(file:File,year:any):Observable<any> {
  
      const formData = new FormData();
        
      formData.append('file', file);
        
    console.log(file.name,year)
      return this.http.post(this.baseApiUrl+'/upload', formData);
  
   }

   download(option:string,value:string):Observable<Employee[]>{
    const ParamData = new FormData();
        
    ParamData.append('option', option);
    ParamData.append('value',value);
    return this.http.post<Employee[]>(this.baseApiUrl+'/getData',ParamData);
   }

   


   getMstTable():Observable<MstTableData>{
    return this.http.get<MstTableData>(this.baseApiUrl+'/getmsttable');
   }
   

}
