package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/15/2021
 */
@Entity
@Table(name = "users_roles")
public class UsersRoles  extends BaseEntity {
    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "role_id")
    private Long roleId;
    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
