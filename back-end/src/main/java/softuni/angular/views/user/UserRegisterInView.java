package softuni.angular.views.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 */
public class UserRegisterInView implements Serializable {
    private String username;
    private String password;
    private String confirmPassword;

    @JsonProperty(value = "username", required = true)
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setSetUsername(String username) {
        this.username = username;
    }

    @JsonProperty(value = "password", required = true)
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(value = "confirmPassword", required = true)
    @NotNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
