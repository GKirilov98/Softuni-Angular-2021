import {Component, OnDestroy, OnInit} from '@angular/core';
import NomenclatureModel from "../../../shared/models/nomenclature/nomenclature.model";
import {InsCompanyDropdownModel} from "../../../shared/models/ins-company/ins-company-dropdown.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {InsProductService} from "../ins-product.service";
import {NomenclatureService} from "../../../shared/services/nomenclature.service";
import {NotificationsService} from "../../../shared/services/notifications.service";
import {ActivatedRoute, Router} from "@angular/router";
import {InsCompanyService} from "../../ins-company/ins-company.service";
import {InsProductCreateModel} from "../../../shared/models/ins-product/ins-product-create.model";
import {InsProductEditModel} from "../../../shared/models/ins-product/ins-product-edit.model";

@Component({
  selector: 'app-ins-product-edit',
  templateUrl: './ins-product-edit.component.html',
  styleUrls: ['../../../shared/css/forms.component.css']
})
export class InsProductEditComponent implements OnInit,OnDestroy {
  nInsTypes!: NomenclatureModel[];
  registerForm!: FormGroup;
  observablesUnsubscribe: Subscription[] = [];
  companyName!: string;
  companyId!: number;
  productId: number;

  constructor(
    private insProductService: InsProductService,
    private nomenclatureService: NomenclatureService,
    private notificationService: NotificationsService,
    private router: Router,
    private insCompanyService: InsCompanyService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let subscription = this.nomenclatureService.getAllNInsTypes()
      .subscribe(data => this.nInsTypes = data);
    this.observablesUnsubscribe.push(subscription)
    this.activateRoute.params.subscribe(data => this.productId = data['id']);
    this.insProductService.getOneById(this.productId).subscribe(
      data => {
        this.initForm(data[0]);
      }
    )
  }

  private initForm(model: InsProductEditModel) {
    this.companyName = model.insCompanyName;
    this.companyId = model.insCompanyId;
    this.registerForm = new FormGroup({
      'insTypeId': new FormControl(model.insTypeId, [Validators.required]),
      'name': new FormControl(model.name,
        [Validators.required, Validators.minLength(3), Validators.maxLength(50)]
      ),
      'defered': new FormControl(model.defered, [Validators.required, Validators.min(0)]),
      'premium': new FormControl(model.premium, [Validators.required, Validators.min(0)]),
      'comission': new FormControl(model.comission, [Validators.required, Validators.min(0)]),
    });
  }

  onSubmit() {
    let model = new InsProductCreateModel(
      this.registerForm.get('insTypeId').value,
      this.companyId,
      this.registerForm.get('name').value,
      this.registerForm.get('defered').value,
      this.registerForm.get('premium').value,
      this.registerForm.get('comission').value
    )
    let subscription = this.insProductService.updateOne(this.productId, model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно редактиран продукт!");
        this.router.navigate(["/ins-company/details", this.companyId]).then();
      });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
