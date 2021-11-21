package softuni.angular.data.views.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/9/2021
 */
public class PolicyTableOutView implements Serializable {
    private Long id;
    private String identityNumber;
    private String productName;
    private DateTime beginDate;
    private DateTime endDate;
    private BigDecimal sum;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("identityNumber")
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("startDate")
    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    @JsonProperty("endDate")
    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("sum")
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "PolicyTableOutView{" +
                "id=" + id +
                ", identityNumber='" + identityNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyTableOutView that = (PolicyTableOutView) o;
        return Objects.equals(id, that.id) && Objects.equals(identityNumber, that.identityNumber) && Objects.equals(productName, that.productName) && Objects.equals(beginDate, that.beginDate) && Objects.equals(endDate, that.endDate) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityNumber, productName, beginDate, endDate, sum);
    }
}
