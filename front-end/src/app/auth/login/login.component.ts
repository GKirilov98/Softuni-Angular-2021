import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {UserModel} from "../../shared/interfaces/user.model";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../auth.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {
  loginForm!: FormGroup;
  observablesUnsubscribe: Subscription[] =[];

  constructor(
    private authService:AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initLoginForm();
  }

  onSubmit() {
    let model = new UserModel();
    model.username =this.loginForm.get("username").value;
    model.password = this.loginForm.get("password").value;
    let subscription = this.authService.login(model)
      .subscribe(res => {
        this.router.navigate(['/home'])
      })
    this.observablesUnsubscribe.push(subscription);

  }

  private initLoginForm() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
    });
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
