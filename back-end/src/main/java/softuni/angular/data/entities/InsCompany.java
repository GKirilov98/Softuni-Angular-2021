package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "ins_companies")
public class InsCompany extends BaseEntity {
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    @Size(min = 3)
    private String name;
    @Basic
    @Column(name = "bulstat", nullable = false, unique = true, length = 13)
    @Size(min = 9)
    private String bulstat;
    @Basic
    @Column(name = "address", nullable = false, length = 100)
    @Size(min = 9)
    private String address;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "phone", nullable = false, length = 15)
    @Size(min = 4)
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
