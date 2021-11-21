package softuni.angular.cron;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.Role;
import softuni.angular.data.entities.User;
import softuni.angular.repositories.RoleRepository;
import softuni.angular.repositories.UserRepository;
import softuni.angular.services.ResErrorLogService;
import softuni.angular.services.SiteUrlActionsLogService;
import softuni.angular.services.impl.UserDetailsImpl;

import java.util.*;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/21/2021
 */
@Component
public class CronRunner {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private final ResErrorLogService resErrorLogService;
    private final SiteUrlActionsLogService siteUrlActionsLogService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public CronRunner(ResErrorLogService resErrorLogService, SiteUrlActionsLogService siteUrlActionsLogService, RoleRepository roleRepository, UserRepository userRepository) {
        this.resErrorLogService = resErrorLogService;
        this.siteUrlActionsLogService = siteUrlActionsLogService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    //  */X means "every X"
    //  @Scheduled(cron ="0 0 0 * * *" )
    //  second, minute, hour, day of month, month, day(s) of week
    //  (*) means match any
    //@Scheduled(cron = "0 * * * * *") // -> every minutes
    @Scheduled(cron = "0 0 0 * * *") //-> every day at 00:00 AM
    public void deleteLogs() {
        try {
            logger.info("Starting cron cronExpireDriverLicense method!");
            this.setAuthenticate();
            resErrorLogService.deleteAll();
            siteUrlActionsLogService.deleteAll();
        } catch (Exception e) {
            logger.error("Error with cron cronExpireDriverLicense method!", e);
        } finally {
            logger.info("Finished cron cronExpireDriverLicense method!");
        }
    }

    private void setAuthenticate() {
        User user = userRepository.findByUsername("CRON_USER").orElse(null);
        if (user == null) {
            List<Role> roles = this.initRoles();
            user = new User();
            user.setUsername("CRON_USER");
            user.setPassword(UUID.randomUUID().toString());
            user.setRoles(roles);
            user = this.userRepository.save(user);
        }


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(e -> {
            authorities.add(new SimpleGrantedAuthority(e.getCode()));
        });

        UserDetails userDetails = new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private List<Role> initRoles() {
        Set<String> rolesCode = new HashSet<>();
        rolesCode.add("ADMIN");
        rolesCode.add("USER");
        return this.roleRepository.findAllByCodeIn(rolesCode);
    }


}
