package softuni.angular.views.insProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
public class InsProductCompanyTableView implements Serializable {
    private Long id;
    private String name;
    private String insTypeDescription;
    private BigDecimal premiumPercent;
    private BigDecimal comissionPercent;
    private boolean defered;

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

    @JsonProperty("typeDescription")
    public String getInsTypeDescription() {
        return insTypeDescription;
    }

    public void setInsTypeDescription(String insTypeDescription) {
        this.insTypeDescription = insTypeDescription;
    }

    @JsonProperty("premium")
    public BigDecimal getPremiumPercent() {
        return premiumPercent;
    }

    public void setPremiumPercent(BigDecimal premiumPercent) {
        this.premiumPercent = premiumPercent;
    }

    @JsonProperty("comission")
    public BigDecimal getComissionPercent() {
        return comissionPercent;
    }

    public void setComissionPercent(BigDecimal comissionPercent) {
        this.comissionPercent = comissionPercent;
    }

    @JsonProperty("defered")
    public boolean isDefered() {
        return defered;
    }

    public void setDefered(boolean defered) {
        this.defered = defered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsProductCompanyTableView that = (InsProductCompanyTableView) o;
        return defered == that.defered && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(insTypeDescription, that.insTypeDescription) && Objects.equals(premiumPercent, that.premiumPercent) && Objects.equals(comissionPercent, that.comissionPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, insTypeDescription, premiumPercent, comissionPercent, defered);
    }

    @Override
    public String toString() {
        return "InsProductCompanyTableView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", insTypeDescription='" + insTypeDescription + '\'' +
                ", premiumPercent=" + premiumPercent +
                ", comissionPercent=" + comissionPercent +
                ", defered=" + defered +
                '}';
    }
}
