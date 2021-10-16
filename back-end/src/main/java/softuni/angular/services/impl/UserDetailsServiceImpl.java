package softuni.angular.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.User;
import softuni.angular.repositories.UserRepository;
import softuni.angular.repositories.UsersRolesRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<SimpleGrantedAuthority> authorities = this.usersRolesRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole().getCode()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(), username, user.getPassword(), authorities
        );
    }

}
