import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    
  constructor(private localStorageService: LocalStorageService) {}

  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    const userInfo: any = this.localStorageService.getUserInfo();
    const token: string = userInfo ? userInfo.token : '';
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
        ContentType: 'application/json',
        AccessControlAllowOrigin: "*"
        //AccessControlAllowMethods: "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS",
        //AccessControlAllowHeaders: "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers",
        //AccessControlExposeHeaders: "Access-Control-Allow-Origin, Access-Control-Allow-Credentials",
        //AccessControlAllowCredentials : "true"
      }
    });
    return next.handle(request);
  }
  //       response.addHeader("Access-Control-Allow-Origin", "*");
  //       response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
  //       response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
  //       response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
  //       response.addHeader("Access-Control-Allow-Credentials", "true");
  //       response.addIntHeader("Access-Control-Max-Age", 10);
  //       filterChain.doFilter(request, response);
}