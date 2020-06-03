import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { catchError, retry } from 'rxjs/operators';
import { PopupService } from './popup.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    
  constructor(private localStorageService: LocalStorageService,
              private popupService: PopupService) {}

  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    const userInfo: any = this.localStorageService.getUserInfo();
    const token: string = userInfo ? userInfo.token : '';
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next.handle(request).pipe(catchError((error: HttpErrorResponse) => {
      if (error.statusText === "Unknown Error") {
        this.popupService.simplePopup("Service unavailable");
      } else {
        this.popupService.simplePopup(error.message);
      }
      return throwError(error);
    }));
  }
}