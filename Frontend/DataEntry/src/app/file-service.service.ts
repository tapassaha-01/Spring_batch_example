import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileServiceService {

  baseApiUrl = "http://localhost:8080/add";
  constructor(private http:HttpClient) {}


    upload(file:File,year:any):Observable<any> {
  
      // const formData = new FormData();
        
      // formData.append("file", file, file.name);
        
    console.log(file.name,year)
      return this.http.post(this.baseApiUrl, file.name)
  
   }
}
