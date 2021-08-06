package softuni.angular.security.model;

import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/6/2021
 */
public class UserAuthRegistrationModel implements Serializable {
    private String logId;
    private String username;
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

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
