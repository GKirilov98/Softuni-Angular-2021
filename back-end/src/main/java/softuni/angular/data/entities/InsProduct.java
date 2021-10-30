package softuni.angular.data.entities;

import org.hibernate.annotations.Type;
import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */

@Entity
@Table(name = "ins_products")
public class InsProduct extends BaseEntity {
    private NInsType insType;
    private InsCompany insCompany;
    private String name;
    private Boolean defered;
    private BigDecimal premiumPercent;
    private BigDecimal comissionPercent;


    @ManyToOne
    @JoinColumn(name ="n_ins_type_id", nullable = false, referencedColumnName = "id")
    public NInsType getInsType() {
        return insType;
    }

    public void setInsType(NInsType insType) {
        this.insType = insType;
    }

    @ManyToOne
    @JoinColumn(name ="ins_company_id", nullable = false, referencedColumnName = "id")
    public InsCompany getInsCompany() {
        return insCompany;
    }

    public void setInsCompany(InsCompany insCompany) {
        this.insCompany = insCompany;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    @Size(min = 3, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "defered", columnDefinition = "tinyint(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean getDefered() {
        return defered;
    }

    public void setDefered(Boolean defered) {
        this.defered = defered;
    }

    @Basic
    @Column(name = "premium_percent")
    @PositiveOrZero
    public BigDecimal getPremiumPercent() {
        return premiumPercent;
    }

    public void setPremiumPercent(BigDecimal premiumPercent) {
        this.premiumPercent = premiumPercent;
    }

    @Basic
    @Column(name = "comission_percent")
    @PositiveOrZero
    public BigDecimal getComissionPercent() {
        return comissionPercent;
    }

    public void setComissionPercent(BigDecimal comissionPercent) {
        this.comissionPercent = comissionPercent;
    }
}
