import { Component } from '@angular/core';
import { SessionService } from '../service/session.service';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { Employee } from '../interface/EmployeeInterface';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private sessionservice: SessionService, private userservice: UserService, private router: Router){}

  isDepartmentEmplListVisible: boolean = false;
  departmentEmployees:Array<any> = []
  employees: Employee[] = [];

  ngOnInit(){
    this.checkSession()
  }

  checkSession(){
    this.sessionservice.checkSessionPeriodically()
  }

  logoutUser(){
    this.userservice.logoutUser().subscribe()
    this.router.navigate(['/login'], { replaceUrl: true })
  }

  getColleagues(){
    this.userservice.getColleagues().subscribe((data) => {
      this.departmentEmployees = data;
      this.employees = this.filterSubordinates(data);
      console.log(this.departmentEmployees)
      this.isDepartmentEmplListVisible = true;
    })
  }

  filterSubordinates(employees: Employee[]): Employee[] {
    const subordinatesIds = new Set<number>(
      employees.flatMap(emp => emp.subordinates.map(sub => sub.employee_id))
    );
  
    return employees.filter(emp => !subordinatesIds.has(emp.employee_id));
  }

  deleteUser(id: number){
    this.userservice.deleteUser(id).subscribe((data) => {
      console.log(data)
      if(data.response == 'deleted'){
        location.reload()
      }
    })
  }
  
  
}
