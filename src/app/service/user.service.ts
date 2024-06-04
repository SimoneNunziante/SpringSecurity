import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getJobs():Observable<any>{
    return this.http.get("http://localhost:8080/getJobs")
  }

  getManagers():Observable<any>{
    return this.http.get("http://localhost:8080/getManagers")
  }

  getDepartments():Observable<any>{
    return this.http.get("http://localhost:8080/getDepartments")
  }

  registerUser(name:string, surname: string, email: string, password: string, number: string, date: Date, job: number | null, manager: number | null, department: number | null):Observable<any>{
    let payload = {name: name, surname: surname, email: email, password: password, number: number, hire_date: date, job: job, manager: manager, department: department}
    return this.http.post("http://localhost:8080/registerUser", payload)
  }

  loginUser(email: string, password: string):Observable<any>{
    let payload = {email: email, password: password}
    return this.http.post("http://localhost:8080/api/loginUser", payload)
  }

  logoutUser(){
    return this.http.post("http://localhost:8080/api/logout", {})
  }

  getColleagues():Observable<any>{
    return this.http.get("http://localhost:8080/api/colleagues")
  }

  deleteUser(id: number):Observable<any>{
    let payload = {id: id}
    return this.http.post("http://localhost:8080/deleteUser", payload)
  }
}
