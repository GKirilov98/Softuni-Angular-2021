package softuni.angular.data.views.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTableOutView that = (UserTableOutView) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Arrays.equals(roleCode, that.roleCode) && Objects.equals(roleDescription, that.roleDescription);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, roleDescription);
        result = 31 * result + Arrays.hashCode(roleCode);
        return result;
    }

    @Override
    public String toString() {
        return "UserTableOutView{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleCode=" + Arrays.toString(roleCode) +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
