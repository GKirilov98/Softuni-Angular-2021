package softuni.angular.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        String logId = UUID.randomUUID().toString();
        logger.error(String.format("%s: Async execution exception message - %s", logId, throwable.getMessage()));
        logger.error(String.format("%s: Method name - %s", logId, method.getName()));
        for (Object param : obj) {
            logger.error(String.format("%s: Parameter value - %s", logId, param));
        }
    }

}