package softuni.angular.data.models;

import java.io.Serializable;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 */
public class AuthenticateResponseModel implements Serializable {
    private String token;
    private Long id;
    private String username;
    private List<String> roles;

    public AuthenticateResponseModel(String token, Long id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
