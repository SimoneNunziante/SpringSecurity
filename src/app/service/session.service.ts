import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { interval } from 'rxjs';
import { switchMap, filter } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient, private router: Router) { }

  checkSessionPeriodically(){
    interval(120000)
    .pipe(
      filter(() => this.router.url === '/dashboard'),
      switchMap(() => this.http.get<{sessionActive: boolean}>('http://localhost:8080/api/checkSession'))
    )
    .subscribe({
      next: response => {
        if(!response.sessionActive){
          this.router.navigate(['/login']);
          alert('Sessione scaduta')
        }
      },
      error: error => console.error(error)
    })
  }
}
