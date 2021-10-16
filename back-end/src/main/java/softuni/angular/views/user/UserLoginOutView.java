package softuni.angular.views.user;

import java.io.Serializable;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public class UserLoginOutView implements Serializable {
    private String token;
    private String username;
    private List<String> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
