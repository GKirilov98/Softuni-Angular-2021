import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import InsCompanyAddModel from "../../../shared/models/ins-company/ins-company-add.model";
import {InsCompanyService} from "../ins-company.service";
import {NotificationsService} from "../../../shared/services/notifications.service";
import {Router} from "@angular/router";
import CustomValidatorsValidator from "../../../shared/utils/custom-validators.validator";

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
    private router: Router,
    private customValidator: CustomValidatorsValidator
  ) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.registerForm = new FormGroup({
      'name': new FormControl(null, [Validators.required,
                    Validators.minLength(3), Validators.maxLength(50)]),
      'bulstat': new FormControl(null,
        [Validators.required, Validators.minLength(9), Validators.maxLength(13)]
      ),
      'address': new FormControl(null, [Validators.required,
        Validators.minLength(9), Validators.maxLength(100)]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone': new FormControl(null, [Validators.required,
       Validators.minLength(4), Validators.maxLength(15),
        this.customValidator.phoneValidator.bind(this)]),
    });
  }

  onSubmit() {
    let model = new InsCompanyAddModel(
      this.registerForm.get('name').value,
      this.registerForm.get('bulstat').value,
      this.registerForm.get('address').value,
      this.registerForm.get('email').value,
      this.registerForm.get('phone').value
    )
    let subscription = this.insCompanyService.insertOne(model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно добавена компания");
        this.router.navigate(["/ins-company/list"]).then();
      });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
