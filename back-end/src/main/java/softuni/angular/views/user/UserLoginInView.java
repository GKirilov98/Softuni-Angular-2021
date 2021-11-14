package softuni.angular.views.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public class UserLoginInView implements Serializable {
    private String username;
    private String password;

    @JsonProperty
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
