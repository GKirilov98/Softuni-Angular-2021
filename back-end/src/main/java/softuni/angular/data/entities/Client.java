package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    @Basic
    @Column(name = "n_client_type_id")
    private Long clientTypeId;
    @ManyToOne
    @JoinColumn(name = "n_client_type_id", insertable = false, updatable = false)
    private NClientType nClientType;
    @Basic
    @Column(name = "egn_bulstat")
    private String egnBulstat;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String telephone;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "note")
    private String note;

    public Long getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(Long clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public String getEgnBulstat() {
        return egnBulstat;
    }

    public void setEgnBulstat(String egnBulstat) {
        this.egnBulstat = egnBulstat;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public NClientType getnClientType() {
        return nClientType;
    }

    public void setnClientType(NClientType nClientType) {
        this.nClientType = nClientType;
    }
}
