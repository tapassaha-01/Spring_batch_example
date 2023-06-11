import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { MstTableData } from './mst-table-data';


@Injectable({
  providedIn: 'root'
})
export class FileServiceService {
  fetchData() {
   return this.http.get(this.baseApiUrl+"/readLog");
  }

  baseApiUrl = "http://localhost:9090/add";
  constructor(private http:HttpClient) {}


    upload(file:FileList,year:any):Observable<any> {
  
      const formData = new FormData();
       for(let i = 0; i < file.length; i++) {
        formData.append('file', file[i]);
       }
      
      formData.append('sheetName', year);
    console.log(file,year)
      return this.http.post(this.baseApiUrl+'/upload', formData);
  
   }

   download(option:string,value:string):Observable<Employee[]>{
    const ParamData = new FormData();
        
    ParamData.append('option', option);
    ParamData.append('value',value);
    return this.http.post<Employee[]>(this.baseApiUrl+'/getData',ParamData);
   }
   downloadLog(){
  
  const headers = new HttpHeaders().set('attachment', 'text/plain');
  return this.http.get(this.baseApiUrl+'/download', { headers, responseType: 'text' });
  
   }
   


   getMstTable():Observable<MstTableData>{
    return this.http.get<MstTableData>(this.baseApiUrl+'/getmsttable');
   }
   

}
