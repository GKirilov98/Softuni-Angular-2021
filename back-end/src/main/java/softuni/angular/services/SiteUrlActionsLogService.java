package softuni.angular.services;

import softuni.angular.exception.GlobalServiceException;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
public interface SiteUrlActionsLogService {
    /**
     * Insert log for active urls
     * @param logId -
     * @param username -
     * @param method GET/POST/PUT/DELETE
     * @param url - url of request
     * @param token - jwt token-a
     * @throws GlobalServiceException -
     */
    void insertOne(String logId, String username, String method, String url, String token) throws GlobalServiceException;
}
