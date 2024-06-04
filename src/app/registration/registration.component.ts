import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  
})
export class RegistrationComponent {

  constructor(private userservice:UserService, private router: Router){}

  ngOnInit(){
    this.getJobs();
    this.getManagers();
    this.getDepartments();
  }

  emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
  numberRegex = new RegExp('^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}$');
  today: Date = new Date();
  jobs:Array<any> = [];
  managers:Array<any> = [];
  departments:Array<any> = [];
  registratedUser:string = '';
  isUserRegistrated:boolean = false;


  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.pattern(this.emailRegex)]),
    password: new FormControl('', [Validators.required, Validators.pattern(this.passwordRegex)]),
    number: new FormControl('', [Validators.required, Validators.pattern(this.numberRegex)]),
    date: new FormControl(new Date(), [Validators.required]),
    job: new FormControl(null, [Validators.required]),
    manager: new FormControl(null, [Validators.required]),
    department: new FormControl(null, [Validators.required])
  });

  get name(){
    return this.registrationForm.get('name'); 
  }

  get surname(){
    return this.registrationForm.get('surname'); 
  }

  get email() { 
    return this.registrationForm.get('email'); 
  }

  get password(){
    return this.registrationForm.get('password'); 
  }

  get number(){
    return this.registrationForm.get('number'); 
  }

  get hire_date(){
    return this.registrationForm.get('date')
  }

  get job(){
    return this.registrationForm.get('job')
  }

  get manager(){
    return this.registrationForm.get('manager')
  }

  get department(){
    return this.registrationForm.get('department')
  }

  onSubmit() {
    console.log(this.registrationForm.value)
    if(this.registrationForm.valid){
      let name:string = this.registrationForm.get('name')!.value ?? '';
      let surname:string = this.registrationForm.get('surname')!.value ?? '';
      let email:string = this.registrationForm.get('email')!.value ?? '';
      let password:string = this.registrationForm.get('password')!.value ?? '';
      let number:string = this.registrationForm.get('number')!.value ?? '';
      let date:Date = this.registrationForm.get('date')!.value ?? new Date();
      let job: number | null = Number(this.registrationForm.get('job')!.value) || null;
      let manager: number | null = Number(this.registrationForm.get('manager')!.value) || null;
      let department: number | null = Number(this.registrationForm.get('department')!.value) || null;
      this.userservice.registerUser(name, surname, email, password, number, date, job, manager, department).subscribe((data) => {
        console.log(data)
        if(data.exist){
          this.registratedUser = "Utente già esistente";
          this.isUserRegistrated = true;
        }
        else{
          this.registratedUser = `Benvenuto ${name}!`;
          this.isUserRegistrated = true;
        }
        setTimeout(() => {
          this.router.navigate(['login'])
        }, 2000);
      })
    }
  }

  date: Date = new Date();

  getJobs(){
    this.userservice.getJobs().subscribe((data) => {
      this.jobs = data
    })
  }

  getManagers(){
    this.userservice.getManagers().subscribe((data) => {
      console.log(data)
      this.managers = data
    })
  }

  getDepartments(){
    this.userservice.getDepartments().subscribe((data) => {
      this.departments = data
    })
  }
}
