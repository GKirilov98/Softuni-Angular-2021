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
public class PolicyDetailsView implements Serializable {
    private Long id;
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

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "PolicyDetailsView{" +
                "id=" + id +
                ", clientTypeCode='" + clientTypeCode + '\'' +
                ", clientTypeDescription='" + clientTypeDescription + '\'' +
                ", fullName='" + fullName + '\'' +
                ", egnBulstat='" + egnBulstat + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", clientNote='" + clientNote + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", sum=" + sum +
                ", tax=" + tax +
                ", commission=" + commission +
                ", premium=" + premium +
                ", productName='" + productName + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectTypeDescription='" + objectTypeDescription + '\'' +
                ", objectDescription='" + objectDescription + '\'' +
                ", policyNote='" + policyNote + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDetailsView that = (PolicyDetailsView) o;
        return Objects.equals(id, that.id) && Objects.equals(clientTypeCode, that.clientTypeCode) && Objects.equals(clientTypeDescription, that.clientTypeDescription) && Objects.equals(fullName, that.fullName) && Objects.equals(egnBulstat, that.egnBulstat) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(clientNote, that.clientNote) && Objects.equals(identityNumber, that.identityNumber) && Objects.equals(sum, that.sum) && Objects.equals(tax, that.tax) && Objects.equals(commission, that.commission) && Objects.equals(premium, that.premium) && Objects.equals(productName, that.productName) && Objects.equals(objectName, that.objectName) && Objects.equals(objectTypeDescription, that.objectTypeDescription) && Objects.equals(objectDescription, that.objectDescription) && Objects.equals(policyNote, that.policyNote) && Objects.equals(beginDate, that.beginDate) && Objects.equals(endDate, that.endDate) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientTypeCode, clientTypeDescription, fullName, egnBulstat, email, phoneNumber, address, clientNote, identityNumber, sum, tax, commission, premium, productName, objectName, objectTypeDescription, objectDescription, policyNote, beginDate, endDate, creationDate);
    }
}
