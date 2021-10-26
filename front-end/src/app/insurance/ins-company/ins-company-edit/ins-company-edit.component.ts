import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import InsCompanyAddModel from "../../../shared/models/ins-company/ins-company-add.model";
import {InsCompanyService} from "../ins-company.service";
import {NotificationsService} from "../../../shared/notifications/notifications.service";
import {ActivatedRoute, Router} from "@angular/router";
import {InsCompanyDeatilsModel} from "../../../shared/models/ins-company/ins-company-deatils.model";

@Component({
  selector: 'app-ins-company-edit',
  templateUrl: './ins-company-edit.component.html',
  styleUrls: ['../../../shared/css/forms.component.css']
})
export class InsCompanyEditComponent implements OnInit {
  editForm!: FormGroup;
  observablesUnsubscribe: Subscription[] = [];
  id!: number;

  constructor(
    private insCompanyService: InsCompanyService,
    private notificationService: NotificationsService,
    private router: Router,
    private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(data => this.id = data['id']);
    let subscription = this.insCompanyService.getOneById(this.id).subscribe(data => {
      this.initForm(data[0]);
    });
    this.observablesUnsubscribe.push(subscription);
  }

  private initForm(detailsModel: InsCompanyDeatilsModel) {
    this.editForm = new FormGroup({
      'name': new FormControl(detailsModel.name, [Validators.required,
        Validators.minLength(3), Validators.maxLength(50)]),
      'bulstat': new FormControl(detailsModel.bulstat,
        [Validators.required, Validators.minLength(9), Validators.maxLength(13)]
      ),
      'address': new FormControl(detailsModel.address, [Validators.required,
        Validators.minLength(9), Validators.maxLength(100)]),
      'email': new FormControl(detailsModel.email, [Validators.required, Validators.email]),
      'phone': new FormControl(detailsModel.phone, [Validators.required,
        Validators.minLength(4), Validators.maxLength(15),
        this.phoneValidator.bind(this)]),
    });
  }

  phoneValidator(control: FormControl): { [p: string]: boolean } {
    let regex = /^[0-9]+$/;
    if (this.editForm && !regex.test(control.value) ) {
      return {"phoneInvalid": true};
    }

    return null;
  }

  onSubmit() {
    let model = new InsCompanyAddModel(
      this.editForm.get('name').value,
      this.editForm.get('bulstat').value,
      this.editForm.get('address').value,
      this.editForm.get('email').value,
      this.editForm.get('phone').value
    )
    let subscription = this.insCompanyService.updateOne(this.id, model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно редактирана компания");
        this.router.navigate(["/ins-company/list"]).then();
      });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
