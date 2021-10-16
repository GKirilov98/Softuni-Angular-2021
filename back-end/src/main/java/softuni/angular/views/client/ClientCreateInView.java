package softuni.angular.views.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
public class ClientCreateInView implements Serializable {
    @JsonProperty("nClientTypeId")
    @NotNull
    private Long clientTypeId;

    @JsonProperty("egnBulstat")
    @NotNull
    private String egnBulstat;

    @JsonProperty("fullName")
    @NotNull
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty
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
}
