package softuni.angular.services;

import softuni.angular.exception.GlobalServiceException;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
public interface ResErrorLogService {

    /**
     *  Insert error requests in db
     * @param logId -
     * @param method - GET/POST/PUT/DELETE
     * @param url- requested url
     * @param status- response status
     * @param username-
     * @param e- exception
     */
    void insertOne(String logId, String method, String url, Integer status, String username, Exception e) throws GlobalServiceException;

    /**
     * Delete all records
     */
    void deleteAll() throws GlobalServiceException;
}
