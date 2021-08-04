package softuni.angular.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity(name = "users_ins_companies")
public class UsersInsCompanies implements Serializable {
    @Id
    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Basic
    @Column(name = "ins_companies_id")
    private Integer insCompaniesId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInsCompaniesId() {
        return insCompaniesId;
    }

    public void setInsCompaniesId(Integer insCompaniesId) {
        this.insCompaniesId = insCompaniesId;
    }
}
