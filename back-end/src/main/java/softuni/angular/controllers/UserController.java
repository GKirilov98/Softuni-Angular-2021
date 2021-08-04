package softuni.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.angular.services.UserService;
import softuni.angular.views.user.UserLoginInView;
import softuni.angular.views.user.UserLoginOutView;
import softuni.angular.views.user.UserRegisterInView;

import javax.validation.Valid;
import java.util.List;


/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterInView inView){
        this.userService.register(inView);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginInView inView){
        List<UserLoginOutView>list = this.userService.login(inView);
        return ResponseEntity.ok(list);
    }

}
