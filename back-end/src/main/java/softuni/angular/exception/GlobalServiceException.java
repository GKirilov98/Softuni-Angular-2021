package softuni.angular.exception;

/**
 * Project: backend
 * Created by: GKirilov
 */
public class GlobalServiceException extends Exception {
    private final String customMessage;
    public GlobalServiceException(String message, Throwable cause) {
        super(message, cause);
        this.customMessage = message;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
