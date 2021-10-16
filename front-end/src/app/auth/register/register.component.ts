import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth.service";
import {UserRegisterModel} from "./models/user-register.model";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private authService: AuthService
  ) {
  }

  onSubmit() {
    let model = new UserRegisterModel();
    model.username = this.registerForm.get('username').value
    model.password = this.registerForm.get('password').value
    model.confirmPassword = this.registerForm.get('repeatPassword').value
    this.authService.register(model);
  }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'repeatPassword': new FormControl(null, [Validators.required, Validators.minLength(6)
        , this.matchPasswords.bind(this)
      ])
    });
  }

  matchPasswords(control: FormControl): { [p: string]: boolean }{
    if (this.registerForm && control.value !== this.registerForm.get('password').value){
        return {"hasMatches": true};
      }

    return null;
  }
}
