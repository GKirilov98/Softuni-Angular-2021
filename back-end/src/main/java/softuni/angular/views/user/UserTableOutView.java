package softuni.angular.views.user;

import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/13/2021
 */
public class UserTableOutView implements Serializable {
    private Long id;
    private String username;
    private String[] roleCode;
    private String roleDescription;

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

    public String[] getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String[] roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
