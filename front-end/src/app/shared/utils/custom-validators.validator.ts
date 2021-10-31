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
}
