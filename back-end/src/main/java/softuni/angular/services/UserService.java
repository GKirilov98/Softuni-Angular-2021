package softuni.angular.services;

import softuni.angular.views.user.UserLoginInView;
import softuni.angular.views.user.UserLoginOutView;
import softuni.angular.views.user.UserRegisterInView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface UserService {
    void register(UserRegisterInView inView);

    List<UserLoginOutView> login(UserLoginInView inView);
}
