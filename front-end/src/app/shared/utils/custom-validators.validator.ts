import {FormControl} from "@angular/forms";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export default class CustomValidatorsValidator {
  phoneValidator(control: FormControl): { [p: string]: boolean } {
    let regex = /^[0-9]+$/;
    if (!regex.test(control.value) ) {
      return {"phoneInvalid": true};
    }

    return null;
  }

  egnValidator(control: FormControl): { [p: string]: boolean } {
    let regex = /^[0-9]+$/;
    let value:string =  control.value;
    if (value){
      if (!regex.test(value) || value.length != 10) {
        console.log(value)
        return {"egnInvalid": true};
      }
    }


    return null;
  }
}
