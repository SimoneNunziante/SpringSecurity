import { Component } from '@angular/core';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private service: UserService, private router: Router){}

  emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
  isRequestSuccessful:boolean = false;
  isResponseVisible: boolean = false;
  responseMessage: string = '';

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.pattern(this.emailRegex)]),
    password: new FormControl('', [Validators.required, Validators.pattern(this.passwordRegex)])
  });

  get email() { 
    return this.loginForm.get('email'); 
  }

  get password() { 
    return this.loginForm.get('password'); 
  }

  onSubmit() {
    console.log(this.loginForm.value);

    if(this.loginForm.valid){
      let email:string = this.loginForm.get('email')!.value ?? '';
      let password:string = this.loginForm.get('password')!.value ?? '';
      
      this.service.loginUser(email, password).subscribe({
        next: (response) => {
          if(response.sessionCreated){
            this.isRequestSuccessful = true;
            this.isResponseVisible = true;
            this.responseMessage = `Benvenuto ${response.email}`
            setTimeout(()=>{
              this.router.navigate(['dashboard'])
            }, 2000)
          }
        },
        error: (err) => {
          this.isRequestSuccessful = false;
          this.isResponseVisible = true;
          this.responseMessage = 'I dati inseriti sono errati';
          console.log(err)
        }
      })
    }
  }
}
