import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { SnackbarService } from './snackbar.service';
import { jwtDecode } from 'jwt-decode';
import { GlobalConstant } from '../shared/global-constants';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService {

  constructor(private auth:AuthService,
    public route:Router,
    private snackbarService:SnackbarService
  ) { }

  canActive(route:ActivatedRouteSnapshot):boolean {
    let expectedRoleArray = route.data;
    expectedRoleArray = expectedRoleArray.expectedRole;

    const token:any = localStorage.getItem('token');

    var tokenPayload:any;

    try {
      tokenPayload = jwtDecode(token);
    } catch (err) {
      localStorage.clear();
      this.route.navigate(['/']);
    }

    let expectedRole = '';

    for (let i = 0; i < expectedRoleArray.length; i++) {
      if (expectedRoleArray[i] == tokenPayload.role) {
        expectedRole = tokenPayload.role;
      }
    }

    if (tokenPayload.role == 'user' || tokenPayload.role == 'admin') {
      if (this.auth.isAuthenticated() && tokenPayload.role == expectedRole) {
        return true;
      }
      this.snackbarService.openSnackBar(GlobalConstant.unauthourized, GlobalConstant.error);
      this.route.navigate(['/cafe/dashboard']);
      return false;
    } else {
      this.route.navigate(['/']);
      localStorage.clear();
      return false;
    }
  }
}
