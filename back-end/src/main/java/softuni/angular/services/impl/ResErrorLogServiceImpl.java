package softuni.angular.services.impl;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.ResErrorLog;
import softuni.angular.repositories.ResErrorLogRepository;
import softuni.angular.services.ResErrorLogService;
import  org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Service
public class ResErrorLogServiceImpl implements ResErrorLogService {
    private final ResErrorLogRepository resErrorLogRepository;

    public ResErrorLogServiceImpl(ResErrorLogRepository reqErrorLogRepository) {
        this.resErrorLogRepository = reqErrorLogRepository;
    }

    @Async("SpringAsyncPool")
    @Override
    public void insertOne(String logId, String method, String url, Integer status, String username, Exception e) {
        ResErrorLog entity = new ResErrorLog();
        entity.setMethod(method);
        entity.setUrl(url);
        entity.setStatus(status);
        entity.setUsername(username);
        entity.setResponseOn(DateTime.now());

        String errorStackTrace = this.getErrorStackTrace(e, logId);
        entity.setError(errorStackTrace);
        resErrorLogRepository.save(entity);
    }

    /**
     * Връща stack на грешката
     * @param e -
     * @return -
     */
    private String getErrorStackTrace(Exception e,  String logId) {
        if ( e != null){
            int maxLength = 3998 ;
            String stackTraceString = ExceptionUtils.getStackTrace(e);
            final String errorMessage = String.format("%s:%s", logId, stackTraceString);
            stackTraceString = errorMessage.substring(0, Math.min(errorMessage.length(), maxLength));
            return stackTraceString;
        } else {
            return "";
        }
    }
}
