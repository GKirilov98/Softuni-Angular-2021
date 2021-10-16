import {UserModel} from "../../../shared/interfaces/user.model";

export class UserRegisterModel extends UserModel{
  confirmPassword: string;
}
