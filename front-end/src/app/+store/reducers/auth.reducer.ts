import {createReducer, on} from '@ngrx/store'
import * as authAction from '../actions/auth.action';
import {authenticate, login, register} from '../actions/auth.action';
import {IUser} from "../../shared/interfaces/IUser";

export interface IAuthState {
  currUser: IUser | null | undefined
}

export const initialState: IAuthState = {
  currUser: undefined
};

const setCurrUser = (state: IAuthState,
                     action: ReturnType<typeof login> |
                       ReturnType<typeof authenticate> |
                       ReturnType<typeof register>) => {
  return {...state, currUser: action.user};
};

export const authReducer = createReducer<IAuthState>(
  initialState,
  on(authAction.login, setCurrUser),
  on(authAction.authenticate, setCurrUser),
  on(authAction.register, setCurrUser),
  on(authAction.logout, (state) => {
    return {...state, currUser: null}
  })
)
