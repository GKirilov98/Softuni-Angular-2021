package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.angular.data.models.AuthenticateResponseModel;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.AuthenticateService;
import softuni.angular.services.UserService;
import softuni.angular.views.user.UserLoginInView;
import softuni.angular.views.user.UserLoginOutView;
import softuni.angular.views.user.UserRegisterInView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: backend
 * Created by: GKirilov
 */
@Service
public class UserServiceImpl implements UserService {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticateService authenticateService;

    @Override
    public void register(UserRegisterInView inView) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Start register service", logId));
            if (!inView.getPassword().equals(inView.getConfirmPassword())) {
                throw new GlobalServiceException("Паролите не съвпадат", new Throwable("Password doesn't match!"));
            }
            this.authenticateService.registerUser(inView, logId);
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished register service", logId));
        }
    }

    @Override
    public List<UserLoginOutView> login(UserLoginInView inView) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Start login service", logId));
            AuthenticateResponseModel authenticateResponseModel =
                    this.authenticateService.loginUser(inView.getUsername(), inView.getPassword(), logId);
            List<UserLoginOutView> result = new ArrayList<>();
            UserLoginOutView outView = this.modelMapper.map(authenticateResponseModel, UserLoginOutView.class);
            result.add(outView);
            return result;
        } catch (GlobalServiceException e) {
            logger.error(String.format("%s: %s", logId, e.getCustomMessage()), e);
            throw e;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished login service", logId));
        }
    }
}
