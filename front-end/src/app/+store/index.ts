import {authReducer, IAuthState} from "./reducers/auth.reducer";
import {ActionReducerMap} from "@ngrx/store";
import {authenticate} from "./actions/auth.action";

export interface IRootState {
  readonly auth: IAuthState;
}

export const reducers: ActionReducerMap<IRootState> ={
  auth: authReducer
};
