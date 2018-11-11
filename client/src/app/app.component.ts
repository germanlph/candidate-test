import { Component, OnInit } from '@angular/core';
import { UserProfileService } from './services/user-profile.service';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private userService: UserProfileService,
    private authService: AuthService,
    private router: Router) {}

  title = "German's candidate-sample";
  
  get isLoggedIn(): boolean {
    return this.authService.isLoggedIn;
  }

  ngOnInit() {
    
  }

  public logIn() {
    this.router.navigate(['login']);
  }
  
  public logOut() {
    this.authService.logOut();
    this.router.navigate(['']);
  }

}
