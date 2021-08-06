package softuni.angular.security.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.User;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.UserRepository;
import softuni.angular.security.jwt.JwtUtils;
import softuni.angular.security.jwt.UserDetailsImpl;
import softuni.angular.security.model.AuthenticateResponseModel;
import softuni.angular.security.model.UserAuthRegistrationModel;
import softuni.angular.security.service.IAuthenticateService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
@Service
public class IAuthenticateServiceImpl implements IAuthenticateService {
    private final Logger logger = LogManager.getLogger(IAuthenticateServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    //    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    public IAuthenticateServiceImpl(AuthenticationManager authenticationManager,
                                    UserRepository userRepository,
//                                    IRoleRepository roleRepository,
                                    PasswordEncoder encoder, JwtUtils jwtUtils, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthenticateResponseModel loginUser(String username, String password) throws GlobalServiceException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            String logId = UUID.randomUUID().toString();
            logger.warn(String.format("%s: Wrong username or password!", logId));
            throw new GlobalServiceException(logId, "Грешно потребителско име или парола!", "Wrong username or password!");
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
//                userDetails.getEmail(),
                roles);
    }

    @Override
    public String registerUser(UserAuthRegistrationModel model) throws GlobalServiceException {

        if (userRepository.existsByUsername(model.getUsername())) {
            logger.error(String.format("%s: Username is already taken!", model.getLogId()));
            throw new GlobalServiceException(model.getLogId(), "Този username е зает!", "Username is already taken!");
        }


//        int roleCount = this.roleRepository.findAll().size();
//        if (roleCount == 0) {
//            for (RoleType value : RoleType.values()) {
//                RoleEntity role = new RoleEntity();
//                role.setDateRegistration(new Timestamp(System.currentTimeMillis()));
//                role.setCode(value.name());
//                role.setDescription(value.name());
//                role.setName(value);
//
//                this.roleRepository.saveAndFlush(role);
//            }
//        }


        // Create new user's account
        User entityUser = this.modelMapper.map(model, User.class);

//        Integer userCount = this.userRepository.getCount();
//        Set<RoleEntity> roles = new HashSet<>();
//        RoleEntity userRole = roleRepository.findByName(RoleType.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//
//        if (userCount == 0){
//            RoleEntity adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//        }
//
//        entityUser.setRoles(roles);
        entityUser.setPassword(encoder.encode(model.getPassword()));
//        entityUser.setImagePublicId(model.getImagePublicIdentity());
//        entityUser.setDateRegistration(new Timestamp(System.currentTimeMillis()));
        userRepository.save(entityUser);

        return "User registered successfully!";
    }

    @Override
    public String logoutUser(String token) {
        jwtUtils.addLogOutToken(token);
        return "Logout successfully!";
    }

}
