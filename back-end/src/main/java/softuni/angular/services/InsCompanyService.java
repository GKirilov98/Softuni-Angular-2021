package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.views.insCompany.InsCompanyInView;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface InsCompanyService {
    void insertOne(InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest;
}
