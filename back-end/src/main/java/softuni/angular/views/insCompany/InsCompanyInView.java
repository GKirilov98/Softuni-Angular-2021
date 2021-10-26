package softuni.angular.views.insCompany;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
public class InsCompanyInView implements Serializable {
    @JsonProperty("name")
    @NotNull
    private String name;
    @JsonProperty("bulstat")
    @NotNull
    private String bulstat;
    @JsonProperty("address")
    @NotNull
    private String address;
    @JsonProperty("email")
    @Email
    @NotNull
    private String email;
    @JsonProperty("phone")
    @NotNull
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
