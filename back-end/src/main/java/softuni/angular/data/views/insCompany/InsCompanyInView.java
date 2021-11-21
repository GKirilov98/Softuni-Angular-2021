package softuni.angular.data.views.insCompany;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
public class InsCompanyInView implements Serializable {
    private String name;
    private String bulstat;
    private String address;
    private String email;
    private String phone;


    @JsonProperty("name")
    @NotNull(message = "Името е задължително поле!")
    @Size(min = 3, max = 50, message = "Името трябва да е с дължина между 3 и 50 символа!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty("bulstat")
    @NotNull(message = "Булстат е задължително поле!")
    @Size(min = 9, max = 13, message = "Булстат трябва да е с дължина между 9 и 13 символа!")
    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    @JsonProperty("address")
    @NotNull(message = "Адрес е задължително поле!")
    @Size(min = 9, max = 100, message = "Адрес трябва да е с дължина между 9 и 100 символа!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("email")
    @Email
    @NotNull(message = "Имейл е задължително поле!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone")
    @NotNull(message = "Телефонен номер е задължително поле!")
    @Size(min = 4, max = 15, message = "Телефонен номер трябва да е с дължина между 4 и 15 символа!")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
