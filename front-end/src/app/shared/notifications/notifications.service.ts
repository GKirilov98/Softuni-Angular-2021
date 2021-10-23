import { Injectable } from '@angular/core';
import {DialogLayoutDisplay, ToastNotificationInitializer} from "@costlydeveloper/ngx-awesome-popup";

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  constructor() { }

  notifyError(message: string) {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Грешка');
    newToastNotification.setMessage(message);
    // Choose layout color type
    newToastNotification.setConfig({
      LayoutType: DialogLayoutDisplay.DANGER // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    // Simply open the toast
    newToastNotification.openToastNotification$();
  }

  notifyWarning(message: string) {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Предупреждение');
    newToastNotification.setMessage(message);
    // Choose layout color type
    newToastNotification.setConfig({
      LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    // Simply open the toast
    newToastNotification.openToastNotification$();
  }

  notifySuccess(message: string) {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Успешно');
    newToastNotification.setMessage(message);
    // Choose layout color type
    newToastNotification.setConfig({
      LayoutType: DialogLayoutDisplay.SUCCESS // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    // Simply open the toast
    newToastNotification.openToastNotification$();
  }

  notifyInfo(message: string) {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Информация');
    newToastNotification.setMessage(message);
    // Choose layout color type
    newToastNotification.setConfig({
      LayoutType: DialogLayoutDisplay.INFO // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    // Simply open the toast
    newToastNotification.openToastNotification$();
  }
}
