package softuni.angular.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import softuni.angular.services.ResErrorLogService;
import softuni.angular.services.impl.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Component
public class LogErrorWriterInterceptor implements HandlerInterceptor {

    private final ResErrorLogService resErrorLogService;

    public LogErrorWriterInterceptor(ResErrorLogService resErrorLogService) {
        this.resErrorLogService = resErrorLogService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();
        if (status >= 400) {
            Object principal = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            String username;
            String logId;
            if (principal.equals("anonymousUser")) {
                username = "anonymousUser";
                logId = UUID.randomUUID().toString();
            } else {
                UserDetailsImpl currentUser = (UserDetailsImpl) principal;
                logId = currentUser.getRequestId();
                username = currentUser.getUsername();
            }
            resErrorLogService.insertOne(logId,
                    request.getMethod(),
                    request.getRequestURL().toString(),
                    status,
                    username,
                    ex);
        }

    }
}
