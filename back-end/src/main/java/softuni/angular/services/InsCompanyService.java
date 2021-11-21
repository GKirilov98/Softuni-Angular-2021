package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.data.views.insCompany.InsCompanyDetailsView;
import softuni.angular.data.views.insCompany.InsCompanyInView;
import softuni.angular.data.views.insCompany.InsCompanyTableView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface InsCompanyService {
    void insertOne(InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest;

    void updateOne(Long id, InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest;

    List<InsCompanyTableView> getAll(String name, String bulstat) throws GlobalServiceException;

    List<InsCompanyDetailsView> getOneById(Long id) throws GlobalServiceException;

    void deleteOne(Long id) throws GlobalServiceException, GlobalBadRequest;
}
