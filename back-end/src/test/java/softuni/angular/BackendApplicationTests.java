package softuni.angular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import softuni.angular.data.entities.Role;
import softuni.angular.data.entities.User;
import softuni.angular.services.impl.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
