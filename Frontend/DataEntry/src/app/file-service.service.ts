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


    upload(file:File,year:any):Observable<any> {
  
      const formData = new FormData();
        
      formData.append('file', file);
        
    console.log(file.name,year)
      return this.http.post(this.baseApiUrl+'/upload', formData);
  
   }

   download():Observable<Employee[]>{

    return this.http.post<Employee[]>(this.baseApiUrl+'/getData','');
   }

   fetchData(){
    return this.http.get(this.baseApiUrl+'/readLog');
   }


   getMstTable():Observable<MstTableData>{
    return this.http.get<MstTableData>(this.baseApiUrl+'/getmsttable');
   }
   

}
