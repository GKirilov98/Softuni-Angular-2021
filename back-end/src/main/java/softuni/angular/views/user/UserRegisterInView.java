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
    @JsonProperty(value = "username", required = true)
    @NotNull
    private String username;
    @JsonProperty(value = "password", required = true)
    @NotNull
    private String password;
    @JsonProperty(value = "confirmPassword", required = true)
    @NotNull
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setSetUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
