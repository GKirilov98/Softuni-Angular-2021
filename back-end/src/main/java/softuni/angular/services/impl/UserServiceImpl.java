package softuni.angular.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.User;
import softuni.angular.repositories.UserRepository;
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
 * On: 8/4/2021
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(UserRegisterInView inView) {
        if (!inView.getPassword().equals(inView.getConfirmPassword())){
            throw new IllegalArgumentException("Password doesn't match!");
        }

        String hashPass = BCrypt.hashpw(inView.getPassword(), BCrypt.gensalt());
        User user = this.modelMapper.map(inView, User.class);
        user.setPassword(hashPass);
        this.userRepository.save(user);
    }

    @Override
    public List<UserLoginOutView> login(UserLoginInView inView) {

        User user = this.userRepository.findByUsername(inView.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Wrong username or password"));
        boolean isMatch = BCrypt.checkpw(inView.getPassword(), user.getPassword());
        if (!isMatch){
            throw new IllegalArgumentException("Wrong username or password");
        }

        List<UserLoginOutView> result = new ArrayList<>();
        UserLoginOutView outView = new UserLoginOutView();
        outView.setUsername(user.getUsername());
        // TODO: 8/4/2021 Implement security
        outView.setToken(UUID.randomUUID().toString());
        result.add(outView);
        return result;
    }


}
