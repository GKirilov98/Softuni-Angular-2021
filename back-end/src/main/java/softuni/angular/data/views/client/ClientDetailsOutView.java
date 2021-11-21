package softuni.angular.data.views.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
public class ClientDetailsOutView implements Serializable {
    private Long id;
    private String fullName;
    private String objectTypeDescription;
    private String egnBulstat;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("clientTypeDescription")
    public String getObjectTypeDescription() {
        return objectTypeDescription;
    }

    public void setObjectTypeDescription(String objectTypeDescription) {
        this.objectTypeDescription = objectTypeDescription;
    }

    @JsonProperty("egnBulstat")
    public String getEgnBulstat() {
        return egnBulstat;
    }

    public void setEgnBulstat(String egnBulstat) {
        this.egnBulstat = egnBulstat;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDetailsOutView that = (ClientDetailsOutView) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(objectTypeDescription, that.objectTypeDescription) && Objects.equals(egnBulstat, that.egnBulstat) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, objectTypeDescription, egnBulstat, email, phoneNumber, address, note);
    }

    @Override
    public String toString() {
        return "ClientDetailsOutView{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", objectTypeDescription='" + objectTypeDescription + '\'' +
                ", egnBulstat='" + egnBulstat + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
