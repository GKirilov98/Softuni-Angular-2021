package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.views.policy.PolicyCalculationOutView;
import softuni.angular.views.policy.PolicyInsertInView;
import softuni.angular.views.policy.PolicyTableOutView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface PolicyService {
    void insertOne(PolicyInsertInView inView) throws GlobalServiceException, GlobalBadRequest;

    List<PolicyCalculationOutView> getCalculations(Long productId, BigDecimal sum) throws GlobalServiceException;

    List<PolicyTableOutView> getAll(Long clientId) throws GlobalServiceException;
}
