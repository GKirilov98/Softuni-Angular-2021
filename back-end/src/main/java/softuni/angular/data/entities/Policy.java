package softuni.angular.data.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity
@Table(name = "policies")
public class Policy extends BaseEntity {
    private String identityNumber;
    private DateTime creationDate;
    private DateTime beginDate;
    private DateTime endDate;
    private InsProduct insProduct;
    private NInsObjectType insObjectType;
    private Client client;
    private String objectDescription;
    private BigDecimal sum;
    private BigDecimal premia;
    private BigDecimal tax;
    private BigDecimal insComission;
    private String note;

    @Basic
    @Column(name = "identity_number", unique = true, nullable = false)
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }


    @Basic
    @Column(name = "creation_date", columnDefinition = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "begin_date", columnDefinition = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date", columnDefinition = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }


    @ManyToOne
    @JoinColumn(name = "ins_prod_id", nullable = false)
    public InsProduct getInsProduct() {
        return insProduct;
    }

    public void setInsProduct(InsProduct insProduct) {
        this.insProduct = insProduct;
    }


    @ManyToOne
    @JoinColumn(name = "n_ins_object_type_id", nullable = false)
    public NInsObjectType getInsObjectType() {
        return insObjectType;
    }

    public void setInsObjectType(NInsObjectType insObjectType) {
        this.insObjectType = insObjectType;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Basic
    @Column(name = "object_description", nullable = false, columnDefinition = "TEXT")
    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    @Basic
    @Column(name = "sum", nullable = false)
    @PositiveOrZero
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "premia", nullable = false)
    @PositiveOrZero
    public BigDecimal getPremia() {
        return premia;
    }

    public void setPremia(BigDecimal premia) {
        this.premia = premia;
    }

    @Basic
    @Column(name = "tax", nullable = false)
    @PositiveOrZero
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Basic
    @Column(name = "ins_comission", nullable = false)
    @PositiveOrZero
    public BigDecimal getInsComission() {
        return insComission;
    }

    public void setInsComission(BigDecimal insComission) {
        this.insComission = insComission;
    }

    @Basic
    @Column(name = "note", columnDefinition = "TEXT")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
