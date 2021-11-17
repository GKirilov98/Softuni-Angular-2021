package softuni.angular.views.insCompany;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/26/2021
 */
public class InsCompanyTableView implements Serializable {
    private Long id;
    private String name;
    private String bulstat;
    private String email;
    private String phone;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("bulstat")
    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsCompanyTableView that = (InsCompanyTableView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(bulstat, that.bulstat) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bulstat, email, phone);
    }

    @Override
    public String toString() {
        return "InsCompanyTableView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bulstat='" + bulstat + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
