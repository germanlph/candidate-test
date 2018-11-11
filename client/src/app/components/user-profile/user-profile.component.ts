import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserViewModel, UserProfileErrors } from 'src/app/viewmodels/userViewModel';
import { AuthService } from 'src/app/services/auth.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ValidPhone } from 'src/app/directive/valid-phone';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userProfile: UserViewModel;
  profileForm: FormGroup;
  outcome: string;
  errorObj: UserProfileErrors;
  @ViewChild("firstName") firstNameField: ElementRef;
  @ViewChild("lastName") lastNameField: ElementRef;
  @ViewChild("email") emailField: ElementRef;
  @ViewChild("phone") phoneField: ElementRef;

  constructor(private userProfileService: UserProfileService,
    private authService: AuthService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.userProfile = this.authService.appUser;
    
    this.profileForm = this.fb.group({
      firstName: new FormControl(this.userProfile.firstName, Validators.required),
      lastName: new FormControl(this.userProfile.lastName, Validators.required),
      email: new FormControl(this.userProfile.email, [Validators.required, Validators.email]),
      phone: new FormControl(this.userProfile.phone, [Validators.required, ValidPhone])
    });
  }

  public update(): void {
    if (this.profileForm.valid) {
      this.outcome = '';
      this.errorObj = null;
      let updating = new UserViewModel();
      updating.firstName = this.profileForm.get('firstName').value;
      updating.lastName = this.profileForm.get('lastName').value;
      updating.email = this.profileForm.get('email').value;
      updating.phone = this.profileForm.get('phone').value;
      updating.id = this.userProfile.id;
      
      this.userProfileService.update(updating)
      .then(updated => {
        this.userProfile = updated;
        this.profileForm.markAsPristine();
        this.outcome = "Profile updated.";
      })
      .catch(error => {
        console.log(error);
        this.outcome = "Error updating profile.";
        this.populateErrors(error.error);
      })
    }
  }

  private populateErrors(errorsObj: UserViewModel) {
    this.errorObj = errorsObj.validationErrorMap;
    if (this.errorObj.firstName || this.errorObj.firstNameValid) {
      this.firstNameField.nativeElement.focus();
    } else if (this.errorObj.lastName || this.errorObj.lastNameValid) {
      this.lastNameField.nativeElement.focus();
    } else if (this.errorObj.email || this.errorObj.emailDuplicate || this.errorObj.emailValid) {
      this.emailField.nativeElement.focus();
    } else if (this.errorObj.phone) {
      this.phoneField.nativeElement.focus();
    }
  }

}
