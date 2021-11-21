package softuni.angular.services.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.ResErrorLog;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.ResErrorLogRepository;
import softuni.angular.services.ResErrorLogService;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Service
public class ResErrorLogServiceImpl implements ResErrorLogService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ResErrorLogRepository resErrorLogRepository;

    public ResErrorLogServiceImpl(ResErrorLogRepository reqErrorLogRepository) {
        this.resErrorLogRepository = reqErrorLogRepository;
    }

    @Async("SpringAsyncPool")
    @Override
    public void insertOne(String logId, String method, String url, Integer status, String username, Exception e) throws GlobalServiceException {
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            ResErrorLog entity = new ResErrorLog();
            entity.setMethod(method);
            entity.setUrl(url);
            entity.setStatus(status);
            entity.setUsername(username);
            entity.setResponseOn(DateTime.now());

            String errorStackTrace = this.getErrorStackTrace(e, logId);
            entity.setError(errorStackTrace);
            resErrorLogRepository.save(entity);
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished insertOne service", logId));
        }

    }

    @Override
    public void deleteAll() throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteAll service", logId));
            this.resErrorLogRepository.deleteAll();
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteAll service", logId));
        }
    }

    /**
     * Връща stack на грешката
     *
     * @param e -
     * @return -
     */
    private String getErrorStackTrace(Exception e, String logId) {
        if (e != null) {
            int maxLength = 3998;
            String stackTraceString = ExceptionUtils.getStackTrace(e);
            final String errorMessage = String.format("%s:%s", logId, stackTraceString);
            stackTraceString = errorMessage.substring(0, Math.min(errorMessage.length(), maxLength));
            return stackTraceString;
        } else {
            return "";
        }
    }
}
