package softuni.angular.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import softuni.angular.services.SiteUrlActionsLogService;
import softuni.angular.services.impl.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/18/2021
 */
@Component
public class LogAccessWriterInterceptor implements HandlerInterceptor {
    private final SiteUrlActionsLogService service;

    public LogAccessWriterInterceptor(SiteUrlActionsLogService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username;
        String logId;
        String token = request.getHeader("authorization");
        if (principal.equals("anonymousUser")){
            username = "anonymousUser";
            logId = UUID.randomUUID().toString();
        } else {
            UserDetailsImpl currentUser = (UserDetailsImpl) principal;
            logId = currentUser.getRequestId();
            username = currentUser.getUsername();
        }

        service.insertOne(logId, username, request.getMethod(), request.getRequestURL().toString(), token);

        return true;
    }
}
