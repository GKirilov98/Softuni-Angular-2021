import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import CustomValidatorsValidator from "../../shared/utils/custom-validators.validator";
import {NomenclatureService} from "../../shared/services/nomenclature.service";
import NomenclatureModel from "../../shared/models/nomenclature/nomenclature.model";
import {InsProductService} from "../../insurance/ins-product/ins-product.service";
import {InsProductTableModel} from "../../shared/models/ins-product/ins-product-table.model";
import {PolicyCreateModel} from "../../shared/models/policy/policy-create.model";
import {PolicyService} from "../policy.service";
import {NotificationsService} from "../../shared/services/notifications.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-policy-create',
  templateUrl: './policy-create.component.html',
  styleUrls: ['./policy-create.component.css']
})
export class PolicyCreateComponent implements OnInit, OnDestroy {
  clientType: string;
  nInsType!: NomenclatureModel[];
  nInsObjectTypes!: NomenclatureModel[];
  insProducts!: InsProductTableModel[];
  isExistClient: boolean;
  registerForm!: FormGroup;
  observablesUnsubscribe: Subscription[] = [];

  selectedProduct: InsProductTableModel;
  premium: number = 0;
  policySum: number = 0;
  taxPremium: number = 0;
  comission: number = 0;

  constructor(
    private validatorUtils: CustomValidatorsValidator,
    private nomenclatureService: NomenclatureService,
    private productService: InsProductService,
    private policyService: PolicyService,
    private notificationService: NotificationsService,
    private router: Router,
  ) {
    this.clientType = "person";
    this.isExistClient = false;
  }

  ngOnInit(): void {
    this.initForm();
    this.nomenclatureService.getAllNInsTypes().subscribe((data) => {
      this.nInsType = data
    });
    this.nomenclatureService.getAllNInsObjectTypes().subscribe((data) => {
      this.nInsObjectTypes = data;
    })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }

  onSubmit() {
    let model = new PolicyCreateModel(
      this.clientType != 'person',
      this.isExistClient == false,
      this.registerForm.get('name')?.value,
      this.registerForm.get('middleName')?.value,
      this.registerForm.get('lastName')?.value,
      this.registerForm.get('egn')?.value,
      this.registerForm.get('bulstat')?.value,
      this.registerForm.get('email')?.value,
      this.registerForm.get('phoneNumber')?.value,
      this.registerForm.get('address')?.value,
      this.registerForm.get('note')?.value,
      this.registerForm.get('identityNumber')?.value,
      this.registerForm.get('policySum')?.value,
      this.registerForm.get('insProduct')?.value,
      this.registerForm.get('objectName')?.value,
      this.registerForm.get('insObjectType')?.value,
      this.registerForm.get('objectDescription')?.value,
      this.registerForm.get('policyNote')?.value,
      this.registerForm.get('beginDate').value,
      this.registerForm.get('endDate').value,
    )
    let subscription = this.policyService.insertOne(model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно създадена полица!");
        this.router.navigate(["/policy/list"]).then();
      });
    this.observablesUnsubscribe.push(subscription);
  }


  private initForm() {
    this.registerForm = new FormGroup({
        'identityNumber': new FormControl(null, [Validators.required]),
        'policySum': new FormControl(null, [Validators.required, Validators.min(1)]),
        'insProductType': new FormControl('', [Validators.required]),
        'insProduct': new FormControl('', [Validators.required]),
        'objectName': new FormControl(null, [Validators.required]),
        'insObjectType': new FormControl('', [Validators.required]),
        'objectDescription': new FormControl(null, [Validators.required]),
        'policyNote': new FormControl(null, []),
        'beginDate': new FormControl(null, [Validators.required]),
        'endDate': new FormControl(null, [Validators.required])
      }
    )
    this.initClientData()
    this.addPersonInfoForm();
  }

  addPersonInfoForm() {
    this.registerForm.addControl('middleName', new FormControl(null, [Validators.required, Validators.minLength(3)]))
    this.registerForm.addControl('lastName', new FormControl(null, [Validators.required, Validators.minLength(3)]))
    this.registerForm.addControl('egn', new FormControl(null,
      [Validators.required, this.validatorUtils.egnValidator
      ]
    ))
  }

  onChangeClientType(event: any): void {
    this.clientType = (event.target as HTMLInputElement).value
    if (this.clientType === "person") {
      this.registerForm.removeControl("bulstat")
      this.addPersonInfoForm();
    } else {
      this.registerForm.addControl('bulstat', new FormControl(null, [Validators.required]),)
      this.registerForm.removeControl("middleName")
      this.registerForm.removeControl("lastName")
      this.registerForm.removeControl("egn")
    }
  }

  onChangeClientData(event: MouseEvent) {
    let value = (event.target as HTMLInputElement).value;
    this.isExistClient = value != "newClient";
    this.initClientData()
  }

  initClientData() {
    if (!this.isExistClient) {
      this.registerForm.addControl('name', new FormControl(null, [Validators.required, Validators.minLength(3)]))
      this.registerForm.addControl('email', new FormControl(null, [Validators.email]),)
      this.registerForm.addControl('phoneNumber', new FormControl(null, [Validators.required, this.validatorUtils.phoneValidator]),)
      this.registerForm.addControl('address', new FormControl(null, [Validators.required]),)
      this.registerForm.addControl('note', new FormControl(null, []),)
    } else {
      this.registerForm.removeControl('name');
      this.registerForm.removeControl("middleName")
      this.registerForm.removeControl("lastName")
      this.registerForm.removeControl('email');
      this.registerForm.removeControl('phoneNumber');
      this.registerForm.removeControl('address');
      this.registerForm.removeControl('note');
    }
  }

  getProductByType(event: any) {
    let value = event.target.value.split(": ")[1];
    let subscription = this.productService.getAllByTypeId(value)
      .subscribe(data => {
        this.insProducts = data;
      });
    this.observablesUnsubscribe.push(subscription)
  }

  setSelectedProduct(productId: Number) {
    if (productId){
      this.selectedProduct = this.insProducts.find(e => e.id == productId)
      this.setNeededMoney()
    }
  }

  private setNeededMoney() {
    if (this.selectedProduct && this.policySum){
      let subscription = this.policyService.getCalculations(this.selectedProduct.id, this.policySum)
        .subscribe(data => {
          if (data.length > 0) {
            this.premium = data[0].premium;
            this.comission = data[0].commission;
            this.taxPremium = data[0].tax;
          }
        });
      this.observablesUnsubscribe.push(subscription)
    }
  }

  setPolicySum($event: any) {
    this.policySum = +$event.target.value;
    this.setNeededMoney();
  }
}
