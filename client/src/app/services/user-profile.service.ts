import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { LoginViewModel } from '../viewmodels/loginViewModel';
import { UserViewModel } from './../viewmodels/userViewModel';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getUserProfile(id: string): Promise<UserViewModel> {
    let params = new HttpParams().set("id", id);
    let options = {
      params: params
    };
    return this.http.get<UserViewModel>(`${this.apiUrl}/login`, options).toPromise();
  }

  public update(updating: UserViewModel): Promise<UserViewModel> {
    let url = `${this.apiUrl}/profile`;
    return this.http.put<UserViewModel>(url, updating).toPromise();
  }
}
