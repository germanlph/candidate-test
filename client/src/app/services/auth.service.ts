import { Injectable } from '@angular/core';
import { LoginViewModel } from '../viewmodels/loginViewModel';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { UserViewModel } from '../viewmodels/userViewModel';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  appUser: UserViewModel;
  apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient,
    private router: Router) {}

  public isLoggedIn: boolean = false;

  public logIn(model: LoginViewModel): Promise<UserViewModel> {
    return this.http.post<UserViewModel>(`${this.apiUrl}/login`, model).toPromise()
      .then(res => {
        this.appUser = res;
        this.isLoggedIn = true;
        return Promise.resolve(res);
      }).catch(error => {
        this.isLoggedIn = false;
        return Promise.reject(error);
      });
  }

  public logOut(): void {
    this.appUser = null;
    this.isLoggedIn = false;
    this.router.navigate(['/']);
  }
}
