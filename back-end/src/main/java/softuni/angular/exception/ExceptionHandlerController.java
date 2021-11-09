package softuni.angular.exception;

import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import softuni.angular.services.impl.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
@RestControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(GlobalBadRequest.class)
    public ErrorModel handleGlobalBadRequestExceptions(
            GlobalBadRequest ex,
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale) {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDateTime(DateTime.now());
        errorModel.setMessage(ex.getCustomMessage());
        errorModel.setUuid(logId);
        errorModel.setSystemMessage(ex.getMessage());
        errorModel.setPath(request.getRequestURL().toString());
        return errorModel;
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GlobalServiceException.class)
    public ErrorModel handleGlobalServiceExceptionExceptions(
            GlobalBadRequest ex,
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDateTime(DateTime.now());
        errorModel.setMessage(ex.getCustomMessage());
        errorModel.setUuid(request.getAttribute("uuid").toString());
        errorModel.setSystemMessage(ex.getMessage());
        errorModel.setPath(request.getRequestURL().toString());
        return errorModel;
    }
}
