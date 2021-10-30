package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.Role;
import softuni.angular.data.entities.User;
import softuni.angular.data.models.AuthenticateResponseModel;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.RoleRepository;
import softuni.angular.repositories.UserRepository;
import softuni.angular.services.AuthenticateService;
import softuni.angular.utils.jwt.JwtUtils;
import softuni.angular.views.user.UserRegisterInView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    public static final String ADMIN_ROLE_CODE = "ADMIN";
    public static final String USER_ROLE_CODE = "USER";
    private final Logger logger = LogManager.getLogger(AuthenticateServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public AuthenticateResponseModel loginUser(String username, String password, String logId) throws GlobalServiceException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            logger.warn(String.format("%s: Wrong username or password!", logId));
            throw new GlobalServiceException("Грешно потребителско име или парола!",
                    new Throwable("Wrong username or password!")
            );
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new AuthenticateResponseModel(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterInView model, String logId) throws GlobalServiceException {
        if (userRepository.existsByUsername(model.getUsername())) {
            logger.error(String.format("%s: Username is already taken!", logId));
            throw new GlobalServiceException("Това потребителско име е заето", new Throwable("Username is already taken!"));
        }

        int roleCount = this.roleRepository.findAll().size();
        if (roleCount == 0) {
            Role role = new Role();
            role.setCode(ADMIN_ROLE_CODE);
            role.setDescription("Админ");
            role.setIsActive(true);

            Role role2 = new Role();
            role2.setCode(USER_ROLE_CODE);
            role2.setDescription("Потребител");
            role2.setIsActive(true);
            List<Role> forSave = new ArrayList<>();
            forSave.add(role);
            forSave.add(role2);
            this.roleRepository.saveAllAndFlush(forSave);
        }
        Role role = this.roleRepository.findByCode(USER_ROLE_CODE);

        User user = this.modelMapper.map(model, User.class);
        String hashPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPass);
        user.setIsActive(true);
        user.getRoles().add(role);
        if (this.userRepository.count() == 0) {
            Role adminRole = this.roleRepository.findByCode(ADMIN_ROLE_CODE);
            user.getRoles().add(adminRole);
        }
        this.userRepository.save(user);
    }
}
