import {ToastUserViewTypeEnum} from "@costlydeveloper/ngx-awesome-popup";

export const notificationsConfig={
  ToastCoreConfig: {
    ToastUserViewType: ToastUserViewTypeEnum.STANDARD, // check API documentation ToastUserViewTypeEnum
    AutoCloseDelay: 1500, // Milliseconds it will be ignored if buttons are included.
  },
  GlobalSettings: {
    AllowedNotificationsAtOnce: 4  // The number of toast notifications that can be shown at once.
  }
}
