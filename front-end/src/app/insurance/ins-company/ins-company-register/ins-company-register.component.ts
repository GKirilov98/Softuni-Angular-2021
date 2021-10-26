import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import InsCompanyAddModel from "../../../shared/models/ins-company/ins-company-add.model";
import {InsCompanyService} from "../ins-company.service";
import {NotificationsService} from "../../../shared/notifications/notifications.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-ins-company-register',
  templateUrl: './ins-company-register.component.html',
  styleUrls: ['../../../shared/css/forms.component.css']
})
export class InsCompanyRegisterComponent implements OnInit, OnDestroy {
  registerForm!: FormGroup;
  observablesUnsubscribe: Subscription[] = [];

  constructor(
    private insCompanyService: InsCompanyService,
    private notificationService: NotificationsService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.registerForm = new FormGroup({
      'name': new FormControl(null, [Validators.required, Validators.minLength(3)]),
      'bulstat': new FormControl(null,
        [Validators.required, Validators.minLength(9), Validators.maxLength(13)]
      ),
      'address': new FormControl(null, [Validators.required]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone': new FormControl(null, [Validators.required, Validators.maxLength(15), this.phoneValidator.bind(this)]),
    });
  }

  phoneValidator(control: FormControl): { [p: string]: boolean } {
    let regex = /^[0-9]+$/;
    if (this.registerForm && !regex.test(control.value) ) {
      return {"phoneInvalid": true};
    }

    return null;
  }

  onSubmit() {
    let model = new InsCompanyAddModel(
      this.registerForm.get('name').value,
      this.registerForm.get('bulstat').value,
      this.registerForm.get('address').value,
      this.registerForm.get('email').value,
      this.registerForm.get('phone').value
    )
    this.insCompanyService.insertOne(model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно добавена компания");
        this.router.navigate(["/company/list"])
      })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
