package softuni.angular.views.insProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
public class InsProductDetailsView implements Serializable {
    private Long id;
    private Long insTypeId;
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
}
