package softuni.angular.services;

import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.views.client.ClientCreateInView;
import softuni.angular.views.client.ClientDetailsOutView;
import softuni.angular.views.client.ClientTableOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface ClientService {

    List<ClientTableOutView> getAll() throws GlobalServiceException;

    void deleteOneById(Long id) throws GlobalBadRequest, GlobalServiceException;
}
