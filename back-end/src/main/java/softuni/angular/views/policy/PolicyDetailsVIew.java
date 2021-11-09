package softuni.angular.views.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/9/2021
 */
public class PolicyDetailsVIew implements Serializable {
    private String clientTypeCode;
    private String clientTypeDescription;
    private String fullName;
    private String egnBulstat;
    private String email;
    private String phoneNumber;
    private String address;
    private String clientNote;
    private String identityNumber;
    private BigDecimal sum;
    private BigDecimal tax;
    private BigDecimal commission;
    private BigDecimal premium;
    private String productName;
    private String objectName;
    private String objectTypeDescription;
    private String objectDescription;
    private String policyNote;
    private DateTime beginDate;
    private DateTime endDate;
    private DateTime creationDate;

    @JsonProperty("clientTypeCode")
    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    @JsonProperty("clientTypeDescription")
    public String getClientTypeDescription() {
        return clientTypeDescription;
    }

    public void setClientTypeDescription(String clientTypeDescription) {
        this.clientTypeDescription = clientTypeDescription;
    }

    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("egnBulstat")
    public String getEgnBulstat() {
        return egnBulstat;
    }

    public void setEgnBulstat(String egnBulstat) {
        this.egnBulstat = egnBulstat;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("clientNote")
    public String getClientNote() {
        return clientNote;
    }

    public void setClientNote(String clientNote) {
        this.clientNote = clientNote;
    }

    @JsonProperty("identityNumber")
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @JsonProperty("sum")
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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

    @JsonProperty("premium")
    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("objectName")
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @JsonProperty("objectTypeDescription")
    public String getObjectTypeDescription() {
        return objectTypeDescription;
    }

    public void setObjectTypeDescription(String objectTypeDescription) {
        this.objectTypeDescription = objectTypeDescription;
    }

    @JsonProperty("objectDescription")
    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    @JsonProperty("policyNote")
    public String getPolicyNote() {
        return policyNote;
    }

    public void setPolicyNote(String policyNote) {
        this.policyNote = policyNote;
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

    @JsonProperty("creationDate")
    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}
