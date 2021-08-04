package softuni.angular.data.entities;

import org.joda.time.DateTime;
import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "policies")
public class Policy extends BaseEntity {
    @Basic
    @Column(name = "no")
    private String no;

    @Basic
    @Column(name = "creation_date", columnDefinition = "DATE")
    private DateTime creationDate;

    @Basic
    @Column(name = "begin_date", columnDefinition = "DATE")
    private DateTime beginDate;

    @Basic
    @Column(name = "end_date", columnDefinition = "DATE")
    private DateTime endDate;

    @Basic
    @Column(name = "ins_prod_id")
    private Integer insProdId;

    @Basic
    @Column(name = "ins_object_type_id")
    private Integer insObjectTypeId;

    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    @Basic
    @Column(name = "object_description")
    private String objectDescription;

    @Basic
    @Column(name = "is_active")
    private Boolean isActive;

    @Basic
    @Column(name = "sum")
    private BigDecimal sum;

    @Basic
    @Column(name = "premia")
    private BigDecimal premia;

    @Basic
    @Column(name = "tax")
    private BigDecimal tax;

    @Basic
    @Column(name = "ins_comiss")
    private BigDecimal insComission;

    @Basic
    @Column(name = "note")
    private String note;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getInsProdId() {
        return insProdId;
    }

    public void setInsProdId(Integer insProdId) {
        this.insProdId = insProdId;
    }

    public Integer getInsObjectTypeId() {
        return insObjectTypeId;
    }

    public void setInsObjectTypeId(Integer insObjectTypeId) {
        this.insObjectTypeId = insObjectTypeId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getPremia() {
        return premia;
    }

    public void setPremia(BigDecimal premia) {
        this.premia = premia;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getInsComission() {
        return insComission;
    }

    public void setInsComission(BigDecimal insComission) {
        this.insComission = insComission;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
