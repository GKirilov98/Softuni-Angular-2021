export class ErrorReciveModel{
  private _status: number;
  private _message: string;

  get status(): number {
    return this._status;
  }

  set status(value: number) {
    this._status = value;
  }

  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }
}
