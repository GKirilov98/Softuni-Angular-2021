package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.views.insProduct.InsProductInView;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface InsProductService {
    void insertOne(InsProductInView inView) throws GlobalServiceException, GlobalBadRequest;
}
