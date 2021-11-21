package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.Role;
import softuni.angular.data.entities.User;
import softuni.angular.data.models.AuthenticateResponseModel;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.RoleRepository;
import softuni.angular.repositories.UserRepository;
import softuni.angular.services.AuthenticateService;
import softuni.angular.services.UserService;
import softuni.angular.data.views.user.UserLoginInView;
import softuni.angular.data.views.user.UserLoginOutView;
import softuni.angular.data.views.user.UserRegisterInView;
import softuni.angular.data.views.user.UserTableOutView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 */
@Service
public class UserServiceImpl implements UserService {
    public static final String ADMIN_ROLE_CODE= "ADMIN";
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final AuthenticateService authenticateService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper modelMapper, AuthenticateService authenticateService, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.authenticateService = authenticateService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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

    @Override
    public List<UserTableOutView> getAll() throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
           return this.userRepository.findAll()
                    .stream()
                    .filter(e -> !e.getUsername().equals(currentUser.getUsername()))
                    .map(e -> {
                        UserTableOutView map = this.modelMapper.map(e, UserTableOutView.class);
                        List<Role> roles = e.getRoles();
                        map.setRoleCode(roles.stream().map(Role::getCode).toArray(String[]::new));
                        map.setRoleDescription(roles.stream().map(Role::getDescription).collect(Collectors.joining(", ")));
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }

    @Override
    public void addRemoveAdminRole(Long id) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
            User user = this.userRepository.findById(id).orElse(null);
            if (user != null){
                if (user.getId() == 1){
                    throw new GlobalBadRequest("Не може да се премахнат/добавят правата на първичния потребител!",
                            new Throwable("Could not be removed roles"));
                }

                Role role = this.roleRepository.findByCode(ADMIN_ROLE_CODE);
                List<Role> roles = user.getRoles();
               boolean isAdmin = roles.stream().anyMatch(e -> e.getCode().equals(role.getCode()));
                if (isAdmin){
                    roles.removeIf(r -> r.getId().equals(role.getId()));
                } else {
                    roles.add(role);
                }
                this.userRepository.save(user);
            }
        } catch (GlobalBadRequest exc){
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }

    @Override
    public void deleteUserByUserId(Long id) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
            User user = this.userRepository.findById(id).orElse(null);
            if (user != null){
                if (user.getId() == 1){
                    throw new GlobalBadRequest("Не може да се изтрие първичния потребител!",
                            new Throwable("Could not be deleted"));
                }
                this.userRepository.delete(user);
            }
        } catch (GlobalBadRequest exc){
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        }

        catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }
}
