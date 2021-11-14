package softuni.angular.views.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/11/2021
 */
public class ClientTableOutView implements Serializable {
    private Long id;
    private String fullName;
    private String objectTypeDescription;
    private String egnBulstat;
    private String email;
    private String phoneNumber;

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
}