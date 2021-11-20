package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.SiteUrlActionsLog;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.SiteUrlActionsLogRepository;
import softuni.angular.services.SiteUrlActionsLogService;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Service
public class SiteUrlActionsLogServiceImpl implements SiteUrlActionsLogService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SiteUrlActionsLogRepository siteUrlActionsRepository;

    public SiteUrlActionsLogServiceImpl(SiteUrlActionsLogRepository siteUrlActionsRepository) {
        this.siteUrlActionsRepository = siteUrlActionsRepository;
    }

    @Async("SpringAsyncPool")
    @Override
    public void insertOne(String logId, String username, String method, String url,
                          String token) throws GlobalServiceException {
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            SiteUrlActionsLog entity = new SiteUrlActionsLog();
            entity.setMethod(method);
            entity.setUrl(url);
            entity.setUsername(username);
            entity.setRequestToken(token);
            entity.setRequestOn(DateTime.now());

            this.siteUrlActionsRepository.save(entity);
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished insertOne service", logId));
        }
    }
}
