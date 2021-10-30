package softuni.angular.views.insProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/30/2021
 */
public class InsProductInView {
    private Long insTypeId;
    private Long insCompanyId;
    private String name;
    private Boolean defered;
    private BigDecimal premiumPercent;
    private BigDecimal comissionPercent;

    @JsonProperty("insTypeId")
    @NotNull(message = "Тип на продукта е задължително поле!")
    public Long getInsTypeId() {
        return insTypeId;
    }

    public void setInsTypeId(Long insTypeId) {
        this.insTypeId = insTypeId;
    }

    @JsonProperty("insCompanyId")
    @NotNull(message = "Компания е задължително поле!")
    public Long getInsCompanyId() {
        return insCompanyId;
    }

    public void setInsCompanyId(Long insCompanyId) {
        this.insCompanyId = insCompanyId;
    }

    @JsonProperty("name")
    @NotNull(message = "Име е задължително поле!")
    @Size(min = 3, max = 50, message = "Името трябва да е с дължина между 3 и 50 символа!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("defered")
    @NotNull(message = "Разсрочено плащане е задължително поле!")
    public Boolean getDefered() {
        return defered;
    }

    public void setDefered(Boolean defered) {
        this.defered = defered;
    }

    @JsonProperty("premium")
    @NotNull(message = "Премията е задължително поле!")
    @PositiveOrZero(message = "Премията трябва да бъде 0 или полужително число!")
    public BigDecimal getPremiumPercent() {
        return premiumPercent;
    }

    public void setPremiumPercent(BigDecimal premiumPercent) {
        this.premiumPercent = premiumPercent;
    }

    @JsonProperty("comission")
    @NotNull(message = "Комисионната е задължително поле!")
    @PositiveOrZero(message = "Комисионната трябва да бъде 0 или полужително число!")
    public BigDecimal getComissionPercent() {
        return comissionPercent;
    }

    public void setComissionPercent(BigDecimal comissionPercent) {
        this.comissionPercent = comissionPercent;
    }
}
