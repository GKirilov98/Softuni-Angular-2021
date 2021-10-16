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
    @JsonProperty
    @NotNull
    private String username;
    @JsonProperty
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
