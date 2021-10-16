package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/15/2021
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
