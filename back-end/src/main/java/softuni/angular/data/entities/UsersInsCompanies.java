package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "users_ins_companies")
public class UsersInsCompanies extends BaseEntity {
    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "ins_companies_id")
    private Long insCompaniesId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInsCompaniesId() {
        return insCompaniesId;
    }

    public void setInsCompaniesId(Long insCompaniesId) {
        this.insCompaniesId = insCompaniesId;
    }
}
