package softuni.angular.services;

import softuni.angular.exception.GlobalServiceException;
import softuni.angular.data.models.AuthenticateResponseModel;
import softuni.angular.views.user.UserRegisterInView;

/**
 * Project: backend
 * Created by: GKirilov
 */
public interface AuthenticateService {
    AuthenticateResponseModel loginUser(String username, String password, String logId) throws GlobalServiceException;
     void registerUser(UserRegisterInView model, String logId) throws GlobalServiceException ;
}
