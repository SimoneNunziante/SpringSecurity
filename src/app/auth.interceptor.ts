import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Sending request to', req.url, 'at', new Date());
        const authReq = req.clone({
            withCredentials: true 
        });
        return next.handle(authReq);
    }
}