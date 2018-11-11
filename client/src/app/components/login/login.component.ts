import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoginViewModel } from 'src/app/viewmodels/loginViewModel';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,
    private fb: FormBuilder,
    private authService: AuthService) {}

  loginForm: FormGroup;
  loginError: string;

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });

  }

  public login(): void {
    this.loginError = '';
    let model = new LoginViewModel();
    model.username = this.loginForm.get('username').value;
    model.password = this.loginForm.get('password').value;
    this.authService.logIn(model).then(res => {
      this.router.navigate(['profile']);
    }).catch(error => {
      this.loginError = error.error;
    })
  }

}
