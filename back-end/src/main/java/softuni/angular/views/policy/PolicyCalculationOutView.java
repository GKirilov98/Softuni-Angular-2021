package softuni.angular.views.policy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/9/2021
 */
public class PolicyCalculationOutView {
    private BigDecimal premium;
    private BigDecimal tax;
    private BigDecimal commission;

    @JsonProperty("premium")
    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    @JsonProperty("tax")
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @JsonProperty("commission")
    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
