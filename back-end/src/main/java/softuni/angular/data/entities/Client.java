package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    private NClientType clientType;
    private String egnBulstat;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;

    @ManyToOne
    @JoinColumn(name = "n_client_type_id", nullable = false,referencedColumnName = "id")
    public NClientType getClientType() {
        return clientType;
    }

    public void setClientType(NClientType clientType) {
        this.clientType = clientType;
    }

    @Basic
    @Column(name = "egn_bulstat", nullable = false)
    public String getEgnBulstat() {
        return egnBulstat;
    }

    public void setEgnBulstat(String egnBulstat) {
        this.egnBulstat = egnBulstat;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 100)
    @Size(min = 3, max = 100)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String telephone) {
        this.phoneNumber = telephone;
    }

    @Basic
    @Column(name = "address", nullable = true)
    @Size(min = 3, max = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "note", columnDefinition = "TEXT")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
