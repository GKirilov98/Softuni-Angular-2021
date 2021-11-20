package softuni.angular.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.angular.filters.AuthTokenFilter;
import softuni.angular.interceptors.LogAccessWriterInterceptor;
import softuni.angular.interceptors.LogErrorWriterInterceptor;
import softuni.angular.services.impl.UserDetailsServiceImpl;
import softuni.angular.utils.jwt.AuthEntryPointJwt;
import softuni.angular.utils.jwt.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
         jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityWebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final UserDetailsServiceImpl userDetailsService;
    private final LogAccessWriterInterceptor logAccessWriter;
    private final LogErrorWriterInterceptor logErrorWriterInterceptor;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtUtils jwtUtils;

    public SecurityWebConfig(AuthEntryPointJwt unauthorizedHandler, UserDetailsServiceImpl userDetailsService, LogAccessWriterInterceptor logAccessWriter, LogErrorWriterInterceptor logErrorWriterInterceptor, JwtUtils jwtUtils) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
        this.logAccessWriter = logAccessWriter;
        this.logErrorWriterInterceptor = logErrorWriterInterceptor;
        this.jwtUtils = jwtUtils;
    }


    /**
     * Creates a patchMatcher bean that matches case insensitively
     *
     * @return PathMatcher
     */
    @Bean
    public PathMatcher pathMatcher() {
        return new CaseInsensitivePathMatcher();
    }

    /**
     * Overrides the configurePathMatch() method in WebMvcConfigurerAdapter
     * <br/>Allows us to set a custom path matcher, used by the MVC for @RequestMapping's
     *
     * @param configurer -
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPathMatcher(pathMatcher());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logAccessWriter);
        registry.addInterceptor(logErrorWriterInterceptor);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/**")
//                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
