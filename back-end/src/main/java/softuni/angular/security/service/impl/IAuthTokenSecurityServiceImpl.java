package softuni.angular.security.service.impl;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.User;
import softuni.angular.repositories.UserRepository;
import softuni.angular.security.jwt.UserDetailsImpl;
import softuni.angular.security.service.IAuthTokenSecurityService;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
@Component
public class IAuthTokenSecurityServiceImpl implements IAuthTokenSecurityService {
    private final UserRepository repository;

    public IAuthTokenSecurityServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
