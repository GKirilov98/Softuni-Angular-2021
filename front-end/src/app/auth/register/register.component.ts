import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth.service";
import {UserRegisterModel} from "../../shared/models/register/user-register.model";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {NotificationsService} from "../../shared/notifications/notifications.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../../shared/css/forms.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  registerForm!: FormGroup;
  observablesUnsubscribe: Subscription[] =[];

  constructor(
    private authService: AuthService,
    private notificationService: NotificationsService,
    private router: Router
  ) {
  }

  onSubmit() {
    let model = new UserRegisterModel();
    model.username = this.registerForm.get('username').value
    model.password = this.registerForm.get('password').value
    model.confirmPassword = this.registerForm.get('confirmPassword').value
    let subscription = this.authService.register(model).subscribe(
      res => {
        this.notificationService.notifySuccess("Успешно регистриран потребител!")
       this.router.navigate(['/user/login']);
      }
    );
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnInit(): void {
    this.initForm();
  }

  matchPasswords(control: FormControl): { [p: string]: boolean } {
    if (this.registerForm && control.value !== this.registerForm.get('password').value) {
      return {"hasMatches": true};
    }

    return null;
  }

  private initForm() {
    this.registerForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'confirmPassword': new FormControl(null, [Validators.required, this.matchPasswords.bind(this)]),
      'accept': new FormControl(null, [Validators.requiredTrue])
    });
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
