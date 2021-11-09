package softuni.angular.views.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/8/2021
 */
public class PolicyInsertInView {
    private Boolean isLegal;
    private Boolean isNew;
    private String name;
    private String middleName;
    private String lastName;
    private String egn;
    private String bulstat;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private String identityNumber;
    private BigDecimal sum;
    private Long insProductId;
    private String objectName;
    private Long insObjectTypeId;
    private String objectDescription;
    private String policyNote;
    private DateTime beginDate;
    private DateTime endDate;

    @JsonProperty("isLegal")
    public Boolean getIsLegal() {
        return isLegal;
    }

    public void setLegal(Boolean legal) {
        isLegal = legal;
    }

    @JsonProperty("isNew")
    public Boolean getIsNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("egn")
    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    @JsonProperty("bulstat")
    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    @JsonProperty("email")
    @Email(message = "Имейлът е невалиден!")
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

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("identityNumber")
    @NotNull(message = "Идентификационния номер е задължително поле!")
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @JsonProperty("polisySum")
    @Positive(message = "Сумата трябва да е полужително число!")
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @JsonProperty("insProductId")
    @NotNull(message = "Трябва да бъде избран продукт!")
    public Long getInsProductId() {
        return insProductId;
    }

    public void setInsProductId(Long insProductId) {
        this.insProductId = insProductId;
    }

    @JsonProperty("insObjectName")
    @NotNull(message = "Името на обекта е задължително поле!")
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @JsonProperty("insObjectTypeId")
    @NotNull(message = "Типа на обекта е задължително поле!")
    public Long getInsObjectTypeId() {
        return insObjectTypeId;
    }

    public void setInsObjectTypeId(Long insObjectTypeId) {
        this.insObjectTypeId = insObjectTypeId;
    }

    @JsonProperty("insObjectDescription")
    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    @JsonProperty("startDate")
    @NotNull(message ="Начална дата е задължителна!")
    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    @JsonProperty("endDate")
    @NotNull(message ="Крайна дата е задължителна!")
    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("policyNote")
    public String getPolicyNote() {
        return policyNote;
    }

    public void setPolicyNote(String policyNote) {
        this.policyNote = policyNote;
    }
}
