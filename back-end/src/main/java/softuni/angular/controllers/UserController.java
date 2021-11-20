package softuni.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.UserService;
import softuni.angular.views.user.UserLoginInView;
import softuni.angular.views.user.UserLoginOutView;
import softuni.angular.views.user.UserRegisterInView;
import softuni.angular.views.user.UserTableOutView;

import javax.validation.Valid;
import java.util.List;


/**
 * Project: backend
 * Created by: GKirilov
 */
@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterInView inView) throws GlobalServiceException {
        this.userService.register(inView);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginInView inView) throws GlobalServiceException {
        List<UserLoginOutView>list = this.userService.login(inView);
        return ResponseEntity.ok(list);
    }

    /**
     *  getAll
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAll() throws GlobalServiceException {
        List<UserTableOutView> list = this.userService.getAll();
        return ResponseEntity.ok(list);
    }

    /**
     *  addRemoveAdminRole
     * @return -
     * @throws GlobalServiceException -
     */
    @PostMapping("/addRemoveAdminRole/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> addRemoveAdminRole(
            @PathVariable Long id
    ) throws GlobalServiceException, GlobalBadRequest {
        this.userService.addRemoveAdminRole(id);
        return ResponseEntity.ok().build();
    }

    /**
     *  deleteUserByUserId
     * @return -
     * @throws GlobalServiceException -
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteUserByUserId(
            @PathVariable Long id
    ) throws GlobalServiceException, GlobalBadRequest {
        this.userService.deleteUserByUserId(id);
        return ResponseEntity.ok().build();
    }
}
