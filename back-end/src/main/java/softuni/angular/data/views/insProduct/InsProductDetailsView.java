package softuni.angular.data.views.insProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
public class InsProductDetailsView implements Serializable {
    private Long id;
    private Long insTypeId;
    private String insTypeDescription;
    private Long insCompanyId;
    private String  insCompanyName;
    private String name;
    private Boolean  defered;
    private BigDecimal premiumPercent;
    private BigDecimal  comissionPercent;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("insTypeId")
    public Long getInsTypeId() {
        return insTypeId;
    }

    public void setInsTypeId(Long insTypeId) {
        this.insTypeId = insTypeId;
    }
    @JsonProperty("insTypeDescription")
    public String getInsTypeDescription() {
        return insTypeDescription;
    }

    public void setInsTypeDescription(String insTypeDescription) {
        this.insTypeDescription = insTypeDescription;
    }

    @JsonProperty("insCompanyId")
    public Long getInsCompanyId() {
        return insCompanyId;
    }

    public void setInsCompanyId(Long insCompanyId) {
        this.insCompanyId = insCompanyId;
    }

    @JsonProperty("insCompanyName")
    public String getInsCompanyName() {
        return insCompanyName;
    }

    public void setInsCompanyName(String insCompanyName) {
        this.insCompanyName = insCompanyName;
    }

    @JsonProperty("defered")
    public Boolean getDefered() {
        return defered;
    }

    public void setDefered(Boolean defered) {
        this.defered = defered;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "InsProductDetailsView{" +
                "id=" + id +
                ", insTypeId=" + insTypeId +
                ", insTypeDescription='" + insTypeDescription + '\'' +
                ", insCompanyId=" + insCompanyId +
                ", insCompanyName='" + insCompanyName + '\'' +
                ", name='" + name + '\'' +
                ", defered=" + defered +
                ", premiumPercent=" + premiumPercent +
                ", comissionPercent=" + comissionPercent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsProductDetailsView that = (InsProductDetailsView) o;
        return Objects.equals(id, that.id) && Objects.equals(insTypeId, that.insTypeId) && Objects.equals(insTypeDescription, that.insTypeDescription) && Objects.equals(insCompanyId, that.insCompanyId) && Objects.equals(insCompanyName, that.insCompanyName) && Objects.equals(name, that.name) && Objects.equals(defered, that.defered) && Objects.equals(premiumPercent, that.premiumPercent) && Objects.equals(comissionPercent, that.comissionPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, insTypeId, insTypeDescription, insCompanyId, insCompanyName, name, defered, premiumPercent, comissionPercent);
    }
}
