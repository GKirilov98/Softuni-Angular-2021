package softuni.angular.exception;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
public class GlobalBadRequest extends Exception {
    private final String customMessage;

    public GlobalBadRequest(String message, Throwable cause) {
        super(message, cause);
        this.customMessage = message;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
