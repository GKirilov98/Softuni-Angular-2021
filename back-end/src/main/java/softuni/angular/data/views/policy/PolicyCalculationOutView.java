package softuni.angular.data.views.policy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "PolicyCalculationOutView{" +
                "premium=" + premium +
                ", tax=" + tax +
                ", commission=" + commission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyCalculationOutView view = (PolicyCalculationOutView) o;
        return Objects.equals(premium, view.premium) && Objects.equals(tax, view.tax) && Objects.equals(commission, view.commission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium, tax, commission);
    }
}
