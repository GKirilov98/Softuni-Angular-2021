package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.data.views.policy.PolicyCalculationOutView;
import softuni.angular.data.views.policy.PolicyDetailsView;
import softuni.angular.data.views.policy.PolicyInsertInView;
import softuni.angular.data.views.policy.PolicyTableOutView;

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

    List<PolicyDetailsView> getOneById(Long id) throws GlobalServiceException;

    void deleteOne(Long id) throws GlobalBadRequest, GlobalServiceException;

    List<PolicyTableOutView> getAllByProductId(Long id) throws GlobalServiceException;

    List<PolicyTableOutView> getAllByClientId(Long id) throws GlobalServiceException;
}
